package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.*;


import com.cigma.gg.service.backoffice.IVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/voiture")
public class VoitureControlleur {
    @Autowired
    IVoitureService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody VoitureDto voitureDto) {
        return service.save(VoitureDto.dtoToEntity(voitureDto));
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public VoitureDto modify(@PathVariable("id") long idvoit, @RequestBody VoitureDto voitureDto) {
        return VoitureDto.entityToDto(service.modify(VoitureDto.dtoToEntity(voitureDto), idvoit));
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idvoit) {
        try {
            service.remove(idvoit);
        } catch (Exception e) {
            System.out.println("Erreur:" + idvoit);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<VoitureDto> getAll() {
        return VoitureDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par matricule
    @GetMapping("/find/{matricule}")
    public List<VoitureDto> Find(@PathVariable("matricule") String matricule) {
        return VoitureDto.entitiesToDtos(service.find(matricule));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public VoitureDto getOne(@PathVariable("id") long idvoit) {
        return VoitureDto.entityToDto(service.getOne(idvoit)) ;
    }
    /**
     * @param matricule
     * @return
     */
    @GetMapping("/matricule/{matricule}")
    public List<VoitureDto> getAllVoitureByMatriculeKeyWord(@PathVariable("matricule") String matricule) {
        return VoitureDto.entitiesToDtos(service.getAllVoitureByMatriculeKeyWord(matricule));
    }

    @GetMapping("/matricule/all")
    public List<VoitureMarticuleDto> getAllUtilisateurByMatriculeKeyWord() {
        return VoitureMarticuleDto.getListMarticule(VoitureDto.entitiesToDtos(service.getAllVoitureMatricule()));
    }
}
