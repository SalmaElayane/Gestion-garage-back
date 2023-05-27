package com.cigma.gg.entity.backoffice;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voiture")
@Entity
public class VoitureEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Les attributs

    private Long id;
    @Column
    private String matricule;
    private String marque;
    private String model;
    private String Type;
    @Lob
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB", nullable = true)
    private byte[] photo;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cin_utilisateur", referencedColumnName = "cin")
    private UtilisateurEntity utilisateurEntity;

    @OneToMany(mappedBy = "voitureEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<MaintenenceEntity> maintenenceEntityList;

}
