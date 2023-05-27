package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;

import java.util.List;

public interface IUtilisateurService {

    //AJOUTER
    SaveResponseDto save(UtilisateurEntity Ult);

    //MODFIER
    UtilisateurEntity modify(UtilisateurEntity Ult, Long idUlt);

    //SUPPRIMER
    void remove(long idUlt);

    //AFFICHER
    List<UtilisateurEntity> getAll();

    //CHERCHER
    List<UtilisateurEntity>  find(String Ult);

    //CHERCHER PAR ID
    UtilisateurEntity getOne(long idUlt);

    /**
     * @param cin
     * @return
     */
    List<UtilisateurEntity> getAllUtilisateurByCinKeyWord(String cin);
    /**
     * @param email
     * @return
     */
    List<UtilisateurEntity> getAllUtilisateurByEmailKeyWord(String email);
    /**
     *
     * @return
     */
    List<UtilisateurEntity> getAllUtilisateurCin();

    List<UtilisateurEntity> getAllUtilisateurEmail();
}
