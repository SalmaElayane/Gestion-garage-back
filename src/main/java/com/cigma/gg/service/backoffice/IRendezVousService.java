package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.RendezVousEntity;

import java.util.List;

public interface IRendezVousService {
    //AJOUTER
    SaveResponseDto save(RendezVousEntity rendezVous);

    //MODFIER
    RendezVousEntity modify(RendezVousEntity rendezVousEntity, Long idrendezVous);

    //VALIDER RENDEZ VOUS
    RendezVousEntity valider(Long idrendezVous);

    //Refuser RENDEZ VOUS
    RendezVousEntity refuser(Long idrendezVous);
    //SUPPRIMER
    void remove(long idrendezVous);

    //AFFICHER
    List<RendezVousEntity> getAll(String statut);

    //CHERCHER par reference
    List<RendezVousEntity> find(String rendezVous);

    //CHERCHER PAR ID
    RendezVousEntity getOne(long rendezVous);
    /**
     * @param refrenece
     * @return
     */
    List<RendezVousEntity> getAllRendezVousByReferenceKeyWord(String refrenece);

    /**
     *
     * @return
     */
    List<RendezVousEntity> getAllRendezVousReference();
}
