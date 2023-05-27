package com.cigma.gg.entity.backoffice;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maintenence")
@Entity
public class MaintenenceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String reference;
    private Date dateDebut;
    private Date dateFin;
    private String etat;
    private String suivi;
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "matricule_voiture", referencedColumnName = "matricule")
    private VoitureEntity voitureEntity;

    @OneToMany(mappedBy = "maintenenceEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<FactureEntity> factureEntityList;
}
