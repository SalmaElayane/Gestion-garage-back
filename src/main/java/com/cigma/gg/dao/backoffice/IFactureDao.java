package com.cigma.gg.dao.backoffice;


import com.cigma.gg.controllers.bachoffice.dto.FactureImprimerDto;
import com.cigma.gg.entity.backoffice.FactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFactureDao extends JpaRepository<FactureEntity, Long> {
    /**
     * @param reference
     * @return
     */
    @Query("SELECT u FROM FactureEntity u WHERE u.reference = ?1")
    FactureEntity findByReference(String reference);

    /**
     * @param reference
     * @return
     */
    List<FactureEntity> findByReferenceContaining(String reference);

    /**
     * @param keyword
     * @return
     */
    @Query("SELECT u FROM FactureEntity u WHERE u.reference LIKE CONCAT('%',:keyword,'%') OR u.maintenenceEntity.reference LIKE CONCAT('%',:keyword,'%')")
    List<FactureEntity> findAllWhereReferenceLike(@Param("keyword") String keyword);

    /**
     * @param idFacture
     * @return
     */
    @Query(value = "SELECT new com.cigma.gg.controllers.bachoffice.dto.FactureImprimerDto(CONCAT(u.nom, ' ', u.prenom), u.cin, " +
            "v.matricule, m.suivi, m.dateDebut, m.dateFin, f.montant, f.reference, f.date) " +
            "FROM FactureEntity f " +
            "INNER JOIN MaintenenceEntity m ON m.reference = f.maintenenceEntity.reference " +
            "INNER JOIN VoitureEntity v ON v.matricule = m.voitureEntity.matricule " +
            "INNER JOIN UtilisateurEntity u ON u.cin = v.utilisateurEntity.cin " +
            "WHERE f.id = ?1")
    FactureImprimerDto getAllFactureInfoById(@Param("idFacture") Long idFacture);
}
