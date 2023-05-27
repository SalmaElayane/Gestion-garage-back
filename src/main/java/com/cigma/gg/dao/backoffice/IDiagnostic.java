package com.cigma.gg.dao.backoffice;


import com.cigma.gg.entity.backoffice.DiagnosticEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiagnostic extends CrudRepository<DiagnosticEntity,Long> {
    DiagnosticEntity findByReference(String reference);
}
