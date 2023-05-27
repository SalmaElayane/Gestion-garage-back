package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.*;
import com.cigma.gg.service.backoffice.IMaintenenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/maintenence")
public class MaintenenceControlleur {

    @Autowired
    IMaintenenceService service;
    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody MaintenenceDto maintenenceDto) {
        return service.save(MaintenenceDto.dtoToEntity(maintenenceDto));
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public MaintenenceDto modify(@PathVariable("id") long idmaint, @RequestBody MaintenenceDto maintenenceDto) {
        return MaintenenceDto.entityToDto(service.modify(MaintenenceDto.dtoToEntity(maintenenceDto), idmaint));
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idmaint) {
        try {
            service.remove(idmaint);
        } catch (Exception e) {
            System.out.println("Erreur:" + idmaint);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<MaintenenceDto> getAll() {
        return MaintenenceDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par matricule
    @GetMapping("/find/{reference}")
    public List<MaintenenceDto> Find(@PathVariable("reference") String reference) {
        return MaintenenceDto.entitiesToDtos(service.getAllMaintenanceByRefAndMatKeyWord(reference));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public MaintenenceDto getOne(@PathVariable("id") long idmaint) {
        return MaintenenceDto.entityToDto(service.getOne(idmaint)) ;
    }
    /**
     * @param reference
     * @return
     */
    @GetMapping("/reference/{reference}")
    public List<MaintenenceDto> getAllVoitureByMatriculeKeyWord(@PathVariable("reference") String reference) {
        return MaintenenceDto.entitiesToDtos(service.getAllMaintenanceByRefAndMatKeyWord(reference));
    }

    @GetMapping("/matricule/all")
    public List<maintenenceReferenceDto> getAllUtilisateurByMatriculeKeyWord() {
        return maintenenceReferenceDto.getListReference(MaintenenceDto.entitiesToDtos(service.getAllMaintenenceReference()));
    }
}
