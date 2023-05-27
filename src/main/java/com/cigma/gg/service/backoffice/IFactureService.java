package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.FactureImprimerDto;
import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.FactureEntity;
import com.itextpdf.text.DocumentException;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFactureService {
    //AJOUTER
    SaveResponseDto save(FactureEntity facture);

    //MODFIER
    FactureEntity modify(FactureEntity factureEntity, Long idfacture);


    //AFFICHER
    List<FactureEntity> getAll();

    //CHERCHER par reference
    List<FactureEntity> find(String facture);

    //CHERCHER PAR ID
    FactureEntity getOne(long facture);

    /**
     * @param refrenece
     * @return
     */
    List<FactureEntity> getAllFactureByRefAndMatKeyWord(String refrenece);

    /**
     * @return
     */
    List<FactureEntity> getAllFacturReference();

    /**
     * @param idFacture
     * @param response
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    InputStream getAllFactureInfoById(Long idFacture, HttpServletResponse response) throws IOException, DocumentException;
}
