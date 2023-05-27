package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.StockEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockDto implements Serializable {
    private Long id;
    private String reference;
    private String nom;
    private String categorie;
    private Long quantite;
    private Double prix;
    private Double total;

    /**
     * @param stockEntity
     * @return
     */
    public static StockDto entityToDto(StockEntity stockEntity) {
        StockDto stockDto = new StockDto();
        if (stockEntity != null) {
            stockDto.setId(stockEntity.getId());
            stockDto.setReference(stockEntity.getReference());
            stockDto.setNom(stockEntity.getNom());
            stockDto.setCategorie(stockEntity.getCategorie());
            stockDto.setQuantite(stockEntity.getQuantite());
            stockDto.setPrix(stockEntity.getPrix());
            stockDto.setTotal(stockEntity.getPrix() * stockEntity.getQuantite());
        }
        return stockDto;
    }

    /**
     * @param stockEntities
     * @return
     */
    public static List<StockDto> entitiesToDtos(List<StockEntity> stockEntities) {
        List<StockDto> stockDtos = new ArrayList<StockDto>();
        if (!stockEntities.isEmpty()) {
            stockDtos = stockEntities.stream().map(StockDto::entityToDto).collect(Collectors.toList());
        }
        return stockDtos;
    }
}
