package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.EmployeCinDto;
import com.cigma.gg.controllers.bachoffice.dto.EmployeDto;
import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;


import com.cigma.gg.controllers.bachoffice.dto.UtilisateurDto;
import com.cigma.gg.entity.backoffice.EmployeEntity;
import com.cigma.gg.service.backoffice.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employe")
public class EmployeControlleur {
    @Autowired
    IEmployeService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody EmployeEntity empl) {
        return service.save(empl);
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public EmployeDto modify(@PathVariable("id") long idempl, @RequestBody EmployeDto employeDto) {
        return EmployeDto.entityToDto(service.modify(EmployeDto.dtoToEntity(employeDto), idempl));
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idempl) {
        try {
            service.remove(idempl);

        } catch (Exception e) {
            System.out.println("Erreur:" + idempl);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<EmployeDto> getAll() {
        return EmployeDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par cin
    @GetMapping("/find/{cin}")
    public List<EmployeDto>  Find(@PathVariable("cin") String cin) {
        return EmployeDto.entitiesToDtos(service.Find(cin));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public EmployeDto getOne(@PathVariable("id") long idempl) {
        return EmployeDto.entityToDto(service.getOne(idempl)) ;
    }
    /**
     * @param cin
     * @return
     */
    @GetMapping("/cin/{cin}")
    public List<EmployeDto> getAllUtilisateurByCinKeyWord(@PathVariable("cin") String cin) {
        return EmployeDto.entitiesToDtos(service.getAllUtilisateurByCinKeyWord(cin));
    }

    @GetMapping("/cin/all")
    public List<EmployeCinDto> getAllUtilisateurByCinKeyWord() {
        return EmployeCinDto.getListCin(EmployeDto.entitiesToDtos(service.getAllEmployeCin()));
    }
}
