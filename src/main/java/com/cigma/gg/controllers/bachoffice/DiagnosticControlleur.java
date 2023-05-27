package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.DiagnosticEntity;
import com.cigma.gg.service.backoffice.IDiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/diagnostic")
public class DiagnosticControlleur {
    @Autowired
    IDiagnosticService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody DiagnosticEntity diagnosticEntity) {
        return service.save(diagnosticEntity);
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public DiagnosticEntity modify(@PathVariable("id") long iddiag, @RequestBody DiagnosticEntity diagnosticEntity) {
        return service.modify(diagnosticEntity, iddiag);
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long iddiag) {
        try {
            service.remove(iddiag);
        } catch (Exception e) {
            System.out.println("Erreur:" + iddiag);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<DiagnosticEntity> getAll() {

        return service.getAll();
    }

    //Pour chercher par reference
    @GetMapping("/find/{reference}")
    public DiagnosticEntity Find(@PathVariable("reference") String reference) {

        return service.Find(reference);
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public DiagnosticEntity getOne(@PathVariable("id") long iddiag) {
        return service.getOne(iddiag);
    }
}
