package com.cigma.gg.dao.backoffice;

import com.cigma.gg.entity.backoffice.EmployeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeDao extends JpaRepository<EmployeEntity,Long> {

    /**
     * @param cin
     * @return
     */
    @Query("SELECT u FROM EmployeEntity u WHERE u.cin = ?1")
    EmployeEntity findByCin(String cin);

    /**
     * @param cin
     * @return
     */
    @Query("SELECT u FROM EmployeEntity u WHERE u.cin LIKE CONCAT('%',:cin,'%')")
    List<EmployeEntity> findAllWhereCinLike(@Param("cin") String cin);
}
