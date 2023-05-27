package com.cigma.gg.dao.backoffice;


import com.cigma.gg.entity.backoffice.MaintenenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMaintenenceDao extends JpaRepository<MaintenenceEntity,Long> {
    /**
     * @param reference
     * @return
     */
    @Query("SELECT u FROM MaintenenceEntity u WHERE u.reference = ?1")
    MaintenenceEntity findByReference(String reference);

    /**
     * @param reference
     * @return
     */
    List<MaintenenceEntity> findByReferenceContaining(String reference);

    /**
     * @param keyword
     * @return
     */
    @Query("SELECT u FROM MaintenenceEntity u WHERE u.reference LIKE CONCAT('%',:keyword,'%') OR u.voitureEntity.matricule LIKE CONCAT('%',:keyword,'%')")
    List<MaintenenceEntity> findAllWhereReferenceLike(@Param("keyword") String keyword);
}
