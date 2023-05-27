package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.controllers.bachoffice.dto.StockDto;
import com.cigma.gg.entity.backoffice.StockEntity;
import com.cigma.gg.service.backoffice.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/stock")
public class StockControlleur {
    @Autowired
    IStockService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody StockEntity stock) {
        return service.save(stock);
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public StockEntity modify(@PathVariable("id") long idStk, @RequestBody StockEntity stock) {
        return service.modify(stock, idStk);
    }

    //Pour supprimer par id
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") long idStk) {
        try {
            service.remove(idStk);
        } catch (Exception e) {
            System.out.println("Erreur:" + idStk);
        }
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<StockDto> getAll() {
       return StockDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par reference
    @GetMapping("/find/{reference}")
    public StockEntity Find(@PathVariable("reference") String reference) {
        return service.Find(reference);
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public StockEntity getOne(@PathVariable("id") long idStk) {
        return service.getOne(idStk);
    }
}
