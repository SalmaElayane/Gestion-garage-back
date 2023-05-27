package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.FactureImprimerDto;
import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IFactureDao;
import com.cigma.gg.dao.backoffice.IMaintenenceDao;
import com.cigma.gg.entity.backoffice.FactureEntity;
import com.cigma.gg.entity.backoffice.MaintenenceEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IFactureService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
public class FactureServiceImp implements IFactureService {

    @Autowired
    IFactureDao factureDao;

    @Autowired
    IMaintenenceDao maintenenceDao;


    //AJOUTER
    @Override
    public SaveResponseDto save(FactureEntity factureEntity) {
        SaveResponseDto saveResponseDto = null;
        final MaintenenceEntity maintenenceEntity;
        if (factureEntity.getMaintenenceEntity() != null) {
            maintenenceEntity = maintenenceDao.findByReference(factureEntity.getMaintenenceEntity().getReference());
            factureEntity.setMaintenenceEntity(maintenenceEntity);
        }
        FactureEntity checkRef = factureDao.findByReference(factureEntity.getReference());
        if (checkRef == null) {
            factureDao.save(factureEntity);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     *
     * @param factureEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(FactureEntity factureEntity) {
        FactureEntity checkCin = factureDao.findByReference(factureEntity.getReference());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public FactureEntity modify(FactureEntity newFactureEntity, Long id) {
        FactureEntity factureEntity = factureDao.findById(id).orElseThrow(NoSuchElementException::new);
        boolean canUpdate = checkRefIsAlreadyExist(factureEntity, newFactureEntity);
        FactureEntity facture = null;
        if (canUpdate) {
            factureEntity.setReference(newFactureEntity.getReference());
            factureEntity.setDate(newFactureEntity.getDate());
            factureEntity.setMontant(newFactureEntity.getMontant());
            MaintenenceEntity maintenenceEntity = maintenenceDao.findByReference(newFactureEntity.getMaintenenceEntity().getReference());
            factureEntity.setMaintenenceEntity(maintenenceEntity);
            facture = factureDao.save(factureEntity);
        }
        return facture;
    }

    /**
     * check Reference Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkRefIsAlreadyExist(FactureEntity oldUser, FactureEntity newUser) {
        if (oldUser.getReference().equals(newUser.getReference())) {
            return true;
        } else if (!oldUser.getReference().equals(newUser.getReference()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Refrerence  déja existe!! ");
        }
    }


    //AFFICHER
    @Override
    public List<FactureEntity> getAll() {
        return factureDao.findAll();
    }

    //CHERCHER
    public List<FactureEntity> find(String facture) {
        return factureDao.findByReferenceContaining(facture);
    }

    //CHERCHER PAR ID
    @Override
    public FactureEntity getOne(long idfacture) {
        return factureDao.findById(idfacture).orElseThrow(NoSuchElementException::new);
    }

    /**
     * @param refrenece
     * @return
     */
    @Override
    public List<FactureEntity> getAllFactureByRefAndMatKeyWord(String refrenece) {
        return factureDao.findAllWhereReferenceLike(refrenece);
    }

    /**
     * @return
     */
    @Override
    public List<FactureEntity> getAllFacturReference() {
        return factureDao.findAll();
    }

    @Override
    public InputStream getAllFactureInfoById(Long idFacture, HttpServletResponse response) throws DocumentException {
        FactureImprimerDto factureImprimerDto = factureDao.getAllFactureInfoById(idFacture);
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        PdfPTable table = new PdfPTable(9);
        addTableHeader(table);
        addRows(table, factureImprimerDto);
        addCustomRows(table);

        document.add(table);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("fullName", "cin", "matricule", "suivi", "dateDebut", "dateFin", "montant", "reference", "date")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.setWidthPercentage(100);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, FactureImprimerDto factureImprimerDto) {
        table.addCell(factureImprimerDto.fullName());
        table.addCell(factureImprimerDto.cin());
        table.addCell(factureImprimerDto.matricule());
        table.addCell(factureImprimerDto.suivi());
        table.addCell(convertDateToString(factureImprimerDto.dateDebut()));
        table.addCell(convertDateToString(factureImprimerDto.dateFin()));
        table.addCell(factureImprimerDto.montant());
        table.addCell(factureImprimerDto.reference());
        table.addCell(convertDateToString(factureImprimerDto.date()));
    }

    /**
     * @param date
     * @return
     */
    private String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    private void addCustomRows(PdfPTable table) {
        PdfPCell horizontalAlignCell = new PdfPCell();
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);
    }
}
