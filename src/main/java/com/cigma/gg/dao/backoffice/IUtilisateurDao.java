package com.cigma.gg.dao.backoffice;

import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IUtilisateurDao extends JpaRepository<UtilisateurEntity, Long> {

    /**
     * @param cin
     * @return
     */
    @Query("SELECT u FROM UtilisateurEntity u WHERE u.cin = ?1")
    UtilisateurEntity findByCin(String cin);

    /**
     * @param email
     * @return
     */
    @Query("SELECT u FROM UtilisateurEntity u WHERE u.email = ?1")
    UtilisateurEntity findByEmail(String email);

    /**
     * @param cin
     * @return
     */
    @Query("SELECT u FROM UtilisateurEntity u WHERE u.cin LIKE CONCAT('%',:cin,'%')")
    List<UtilisateurEntity> findAllWhereCinLike(@Param("cin") String cin);

    /**
     * @param cin
     * @return
     */
    @Query("SELECT u FROM UtilisateurEntity u WHERE u.email LIKE CONCAT('%',:email,'%')")
    List<UtilisateurEntity> findAllWhereEmailLike(@Param("email") String cin);

    /**
     * @return
     */
    Optional<UtilisateurEntity> findByLogin(String login);
}
