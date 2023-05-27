package com.cigma.gg.dao.backoffice;


import com.cigma.gg.entity.backoffice.StockEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockDao extends CrudRepository<StockEntity,Long> {
    StockEntity findByReference(String reference);
}
