package com.cigma.gg.entity.backoffice;

import lombok.*;
import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity(name = "stock")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;


    @Column
    private String reference;
    private String nom;
    private String categorie;
    private long quantite;
    private Double prix;
    //CONSTRUCTEUR PAR DEFAULT
    public StockEntity(){ }

    //CONSTRUCTEUR AVEC PARAMETER
    public StockEntity(String reference, String nom, String categorie,long quantite,Double prix) {
        this.reference = reference;
        this.nom = nom;
        this.categorie = categorie;
        this.quantite=quantite;
        this.prix = prix;
    }

}
