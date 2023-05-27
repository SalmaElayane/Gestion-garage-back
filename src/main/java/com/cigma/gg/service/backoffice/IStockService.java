package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.StockEntity;


import java.util.List;

public interface IStockService {
    //AJOUTER
    SaveResponseDto save(StockEntity stock);

    //MODFIER
    StockEntity modify(StockEntity stock, Long idStk);

    //SUPPRIMER
    void remove(long idStk);

    //AFFICHER
    List<StockEntity> getAll();

    //CHERCHER par reference
    StockEntity Find(String stock);

    //CHERCHER PAR ID
    StockEntity getOne(long stock);


}
