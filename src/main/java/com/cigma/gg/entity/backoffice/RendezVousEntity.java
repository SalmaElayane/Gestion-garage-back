package com.cigma.gg.entity.backoffice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rendezVous")
@Entity
public class RendezVousEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column
    private String reference;
    private String type;
    private Date dateRendezVous;
    private String description;
    private String statut;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cin_utilisateur", referencedColumnName = "cin")
    private UtilisateurEntity utilisateurEntity;

}
