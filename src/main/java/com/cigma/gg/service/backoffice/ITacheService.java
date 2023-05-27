package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.TacheEntity;

import java.util.List;

public interface ITacheService {
    //AJOUTER
    SaveResponseDto save(TacheEntity tache);

    //MODFIER
    TacheEntity modify(TacheEntity tacheEntity, Long idtache);

    //SUPPRIMER
    void remove(long idtache);

    //AFFICHER
    List<TacheEntity> getAll();

    //CHERCHER par reference
    List<TacheEntity> find(String tache);

    //CHERCHER PAR ID
    TacheEntity getOne(long tache);
}
