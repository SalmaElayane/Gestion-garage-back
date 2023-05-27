package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.EmployeEntity;


import java.util.List;

public interface IEmployeService {
    //AJOUTER
    SaveResponseDto save(EmployeEntity empl);

    //MODFIER
    EmployeEntity modify(EmployeEntity empl, Long idempl);

    //SUPPRIMER
    void remove(long idempl);

    //AFFICHER
    List<EmployeEntity> getAll();

    //CHERCHER
    List<EmployeEntity> Find(String empl);

    //CHERCHER PAR ID
    EmployeEntity getOne(long empl);

    /**
     * @param cin
     * @return
     */
    List<EmployeEntity> getAllUtilisateurByCinKeyWord(String cin);

    /**
     *
     * @return
     */
    List<EmployeEntity> getAllEmployeCin();
}
