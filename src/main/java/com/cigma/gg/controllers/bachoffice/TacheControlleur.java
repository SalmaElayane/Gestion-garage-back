package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.controllers.bachoffice.dto.TacheDto;

import com.cigma.gg.service.backoffice.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tache")
public class TacheControlleur {
    @Autowired
    ITacheService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody TacheDto tacheDto) {
        return service.save(TacheDto.dtoToEntity(tacheDto));
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public TacheDto modify(@PathVariable("id") long idtache, @RequestBody TacheDto tacheDto) {
        return TacheDto.entityToDto(service.modify(TacheDto.dtoToEntity(tacheDto), idtache));
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idtache) {
        try {
            service.remove(idtache);
        } catch (Exception e) {
            System.out.println("Erreur:" + idtache);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<TacheDto> getAll() {
        return TacheDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par matricule
    @GetMapping("/find/{reference}")
    public List<TacheDto> find(@PathVariable("reference") String reference) {
        return TacheDto.entitiesToDtos(service.find(reference));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public TacheDto getOne(@PathVariable("id") long idtache) {
        return TacheDto.entityToDto(service.getOne(idtache)) ;
    }
}
