package com.cigma.gg.dao.backoffice;

import com.cigma.gg.entity.backoffice.RendezVousEntity;
import com.cigma.gg.entity.backoffice.VoitureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVoitureDao extends JpaRepository<VoitureEntity, Long> {
    /**
     * @param matricule
     * @return
     */
    @Query("SELECT u FROM VoitureEntity u WHERE u.matricule = ?1")
    VoitureEntity findByMatricule(String matricule);

    /**
     * @param keyword
     * @return
     */
    @Query("SELECT u FROM VoitureEntity u WHERE u.matricule LIKE CONCAT('%',:keyword,'%') OR u.utilisateurEntity.cin LIKE CONCAT('%',:keyword,'%')")
    List<VoitureEntity> findAllWhereMatriculeLike(@Param("keyword") String keyword);

}
