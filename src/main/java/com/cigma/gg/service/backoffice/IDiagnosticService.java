package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.DiagnosticEntity;


import java.util.List;

public interface IDiagnosticService {
    //AJOUTER
    SaveResponseDto save(DiagnosticEntity diag);

    //MODFIER
    DiagnosticEntity modify(DiagnosticEntity diag, Long iddiag);

    //SUPPRIMER
    void remove(long iddiag);

    //AFFICHER
    List<DiagnosticEntity> getAll();

    //CHERCHER par reference
    DiagnosticEntity Find(String diag);

    //CHERCHER PAR ID
    DiagnosticEntity getOne(long diag);
}
