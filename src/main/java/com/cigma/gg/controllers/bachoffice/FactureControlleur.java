package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.*;
import com.cigma.gg.service.backoffice.IFactureService;
import com.cigma.gg.service.backoffice.IMaintenenceService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/facture")
public class FactureControlleur {
    @Autowired
    IFactureService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody FactureDto factureDto) {
        return service.save(FactureDto.dtoToEntity(factureDto));
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public FactureDto modify(@PathVariable("id") long idfacture, @RequestBody FactureDto factureDto) {
        return FactureDto.entityToDto(service.modify(FactureDto.dtoToEntity(factureDto), idfacture));
    }


    //Pour afficher tout
    @GetMapping("/all")
    public List<FactureDto> getAll() {
        return FactureDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par reference
    @GetMapping("/find/{reference}")
    public List<FactureDto> Find(@PathVariable("reference") String reference) {
        return FactureDto.entitiesToDtos(service.getAllFactureByRefAndMatKeyWord(reference));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public FactureDto getOne(@PathVariable("id") long idFacture) {
        return FactureDto.entityToDto(service.getOne(idFacture));
    }

    /**
     * @param reference
     * @return
     */
    @GetMapping("/reference/{reference}")
    public List<FactureDto> getAllMaintenanceByRefAndMatKeyWord(@PathVariable("reference") String reference) {
        return FactureDto.entitiesToDtos(service.getAllFactureByRefAndMatKeyWord(reference));
    }

    @GetMapping("/matricule/all")
    public List<FactureReferenceDto> getAllMaintenanceByRefAndMatKeyWord() {
        return FactureReferenceDto.getListReference(FactureDto.entitiesToDtos(service.getAllFacturReference()));
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("/imprimer/{id}")
    public ResponseEntity<InputStreamResource> imprimerFacture(@PathVariable("id") Long id, HttpServletResponse response) throws IOException, DocumentException {
        InputStream fileInputStream = service.getAllFactureInfoById(id, response);
        InputStreamResource fileInputStreamResource = null;
        if (fileInputStream != null)
            fileInputStreamResource = new InputStreamResource(fileInputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture.pdf")
                .contentType(MediaType.parseMediaType("application/pdf")).body(fileInputStreamResource);
    }
}
