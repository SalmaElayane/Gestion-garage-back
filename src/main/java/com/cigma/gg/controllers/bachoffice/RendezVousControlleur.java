package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.RendezVousDto;
import com.cigma.gg.controllers.bachoffice.dto.RendezVousRefDto;
import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;

import com.cigma.gg.service.backoffice.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rendezVous")
public class RendezVousControlleur {
    @Autowired
    IRendezVousService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody RendezVousDto rendezVousDto) {
        return service.save(RendezVousDto.dtoToEntity(rendezVousDto));
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public RendezVousDto modify(@PathVariable("id") long idRv, @RequestBody RendezVousDto rendezVousDto) {
        return RendezVousDto.entityToDto(service.modify(RendezVousDto.dtoToEntity(rendezVousDto), idRv));
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idRv) {
        try {
            service.remove(idRv);
        } catch (Exception e) {
            System.out.println("Erreur:" + idRv);
        }
    }

    //Pour affciher tout
    @GetMapping("/all/{statut}")
    public List<RendezVousDto> getAll(@PathVariable("statut") String statut) {
        return RendezVousDto.entitiesToDtos(service.getAll(statut));
    }

    //Pour chercher par reference
    @GetMapping("/find/{reference}")
    public List<RendezVousDto> Find(@PathVariable("reference") String reference) {
        return RendezVousDto.entitiesToDtos(service.find(reference));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public RendezVousDto getOne(@PathVariable("id") long idvoit) {
        return RendezVousDto.entityToDto(service.getOne(idvoit));
    }

    /**
     * @param reference
     * @return
     */
    @GetMapping("/reference/{reference}")
    public List<RendezVousDto> getAllRendezVousByReferenceKeyWord(@PathVariable("reference") String reference) {
        return RendezVousDto.entitiesToDtos(service.getAllRendezVousByReferenceKeyWord(reference));
    }

    @GetMapping("/reference/all")
    public List<RendezVousRefDto> getAllUtilisateurByMatriculeKeyWord() {
        return RendezVousRefDto.getListReference(RendezVousDto.entitiesToDtos(service.getAllRendezVousReference()));
    }

    /**
     * @param id
     * @return
     */
    @PutMapping("/valider/{id}")
    public RendezVousDto valideeRendezVous(@PathVariable("id") Long id) {
        return RendezVousDto.entityToDto(service.valider(id));
    }

    /**
     * @param id
     * @return
     */
    @PutMapping("/refuser/{id}")
    public RendezVousDto refuserRendezVous(@PathVariable("id") Long id) {
        return RendezVousDto.entityToDto(service.refuser(id));
    }
}
