package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.*;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import com.cigma.gg.service.backoffice.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurControlleur {
    @Autowired
    IUtilisateurService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody UtilisateurEntity Ult) {
        return service.save(Ult);
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public UtilisateurDto modify(@PathVariable("id") long idUlt, @RequestBody UtilisateurDto utilisateurDto) {
        return UtilisateurDto.entityToDto(service.modify(UtilisateurDto.dtoToEntity(utilisateurDto), idUlt));
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idUlt) {
        try {
            service.remove(idUlt);

        } catch (Exception e) {
            System.out.println("Erreur:" + idUlt);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<UtilisateurDto> getAll() {
        return UtilisateurDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par cin
    @GetMapping("/find/{cin}")
    public List<UtilisateurDto> Find(@PathVariable("cin") String cin) {
        return UtilisateurDto.entitiesToDtos(service.find(cin));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public UtilisateurDto getOne(@PathVariable("id") long idUlt) {
        return UtilisateurDto.entityToDto(service.getOne(idUlt)) ;
    }
    /**
     * @param cin
     * @return
     */
    @GetMapping("/cin/{cin}")
    public List<UtilisateurDto> getAllUtilisateurByCinKeyWord(@PathVariable("cin") String cin) {
        return UtilisateurDto.entitiesToDtos(service.getAllUtilisateurByCinKeyWord(cin));
    }

    @GetMapping("/cin/all")
    public List<UtilisateurCinDto> getAllUtilisateurByCinKeyWord() {
        return UtilisateurCinDto.getListCin(UtilisateurDto.entitiesToDtos(service.getAllUtilisateurCin()));
    }
    @GetMapping("/email/{email}")
    public List<UtilisateurDto> getAllUtilisateurByEmailKeyWord(@PathVariable("email") String email) {
        return UtilisateurDto.entitiesToDtos(service.getAllUtilisateurByEmailKeyWord(email));
    }
    @GetMapping("/email/all")
    public List<UtilisateurEmailDto> getAllUtilisateurByEmailKeyWord() {
        return UtilisateurEmailDto.getListEmail(UtilisateurDto.entitiesToDtos(service.getAllUtilisateurEmail()));
    }

}
