package com.cigma.gg.dao.backoffice;


import com.cigma.gg.entity.backoffice.RendezVousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRendezVousDao extends JpaRepository<RendezVousEntity, Long> {
    /**
     * @param reference
     * @return
     */
    @Query("SELECT u FROM RendezVousEntity u WHERE u.reference = ?1")
    RendezVousEntity findByReference(String reference);

    /**
     * @param keyword
     * @return
     */
    @Query("SELECT u FROM RendezVousEntity u WHERE u.reference LIKE CONCAT('%',:keyword,'%') OR u.utilisateurEntity.cin LIKE CONCAT('%',:keyword,'%')")
    List<RendezVousEntity> findAllWhereReferenceLike(@Param("keyword") String keyword);

    /**
     * @param reference
     * @return
     */
    List<RendezVousEntity> findByReferenceContaining(String reference);

    /**
     * @param statut
     * @return
     */
    List<RendezVousEntity> findAllByStatut(String statut);
}
