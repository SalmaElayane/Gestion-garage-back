package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.MaintenenceEntity;



import java.util.List;

public interface IMaintenenceService {
    //AJOUTER
    SaveResponseDto save(MaintenenceEntity maintenence);

    //MODFIER
    MaintenenceEntity modify(MaintenenceEntity maintenenceEntity, Long idmaintenence);

    //SUPPRIMER
    void remove(long idmaintenence);

    //AFFICHER
    List<MaintenenceEntity> getAll();

    //CHERCHER par reference
    List<MaintenenceEntity> find(String maintenence);

    //CHERCHER PAR ID
    MaintenenceEntity getOne(long maintenence);
    /**
     * @param refrenece
     * @return
     */
    List<MaintenenceEntity> getAllMaintenanceByRefAndMatKeyWord(String refrenece);

    /**
     *
     * @return
     */
    List<MaintenenceEntity> getAllMaintenenceReference();
}
