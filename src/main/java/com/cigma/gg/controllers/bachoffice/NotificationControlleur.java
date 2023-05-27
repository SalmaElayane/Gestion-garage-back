package com.cigma.gg.controllers.bachoffice;

import com.cigma.gg.controllers.bachoffice.dto.*;
import com.cigma.gg.service.backoffice.INotificationService;
import com.cigma.gg.service.backoffice.IVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/notification")
public class NotificationControlleur {
    @Autowired
    INotificationService service;

    //Pour ajouter
    @PostMapping("/create")
    public SaveResponseDto save(@RequestBody NotificationDto notificationDto) {
        return service.save(NotificationDto.dtoToEntity(notificationDto));
    }

    //Pour modifier
    @PutMapping("/modify/{id}")
    public NotificationDto modify(@PathVariable("id") long idnotif, @RequestBody NotificationDto notificationDto) {
        return NotificationDto.entityToDto(service.modify(NotificationDto.dtoToEntity(notificationDto), idnotif));
    }

    //Pour affciher tout
    @GetMapping("/all")
    public List<NotificationDto> getAll() {
        return NotificationDto.entitiesToDtos(service.getAll());
    }

    //Pour chercher par reference
    @GetMapping("/find/{reference}")
    public List<NotificationDto> Find(@PathVariable("reference") String reference) {
        return NotificationDto.entitiesToDtos(service.find(reference));
    }

    //Pour chercher par id
    @GetMapping("/{id}")
    public NotificationDto getOne(@PathVariable("id") long idnotif) {
        return NotificationDto.entityToDto(service.getOne(idnotif)) ;
    }
    /**
     * @param reference
     * @return
     */
    @GetMapping("/reference/{reference}")
    public List<NotificationDto> getAllNotificationByReferenceKeyWord(@PathVariable("reference") String reference) {
        return NotificationDto.entitiesToDtos(service.getAllNotificationByReferenceKeyWord(reference));
    }

    @GetMapping("/reference/all")
    public List<NotificationRefDto> getAllNotificationReference() {
        return NotificationRefDto.getListReference(NotificationDto.entitiesToDtos(service.getAllNotificationReference()));
    }
}
