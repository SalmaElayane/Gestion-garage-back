package com.cigma.gg.dao.backoffice;


import com.cigma.gg.entity.backoffice.TacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITacheDao extends JpaRepository<TacheEntity, Long> {
    /**
     * @param reference
     * @return
     */
    TacheEntity findByReference(String reference);

    /**
     * @param keyword
     * @return
     */
    @Query("SELECT u FROM TacheEntity u WHERE u.reference LIKE CONCAT('%',:keyword,'%') OR u.employeEntity.cin LIKE CONCAT('%',:keyword,'%')")
    List<TacheEntity> findByReferenceContaining(@Param("keyword") String keyword);
}
