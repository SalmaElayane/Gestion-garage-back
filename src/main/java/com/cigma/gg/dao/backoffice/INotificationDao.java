package com.cigma.gg.dao.backoffice;


import com.cigma.gg.entity.backoffice.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificationDao extends JpaRepository<NotificationEntity,Long> {
    /**
     * @param reference
     * @return
     */
    @Query("SELECT u FROM NotificationEntity u WHERE u.reference = ?1")
    NotificationEntity findByReference(String reference);

    /**
     * @param reference
     * @return
     */
    @Query("SELECT u FROM NotificationEntity u WHERE u.reference LIKE CONCAT('%',:reference,'%')")
    List<NotificationEntity> findAllWhereReferenceLike(@Param("reference") String reference);

    /**
     * @param reference
     * @return
     */
    List<NotificationEntity> findByReferenceContaining(String reference);
}
