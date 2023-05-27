package com.cigma.gg.entity.backoffice;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tache")
@Entity
public class TacheEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column
    private String reference;
    private String nom;
    private String service;
    private Date date_debut;
    private Date date_fin;
    private long status;
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cin_employe", referencedColumnName = "cin")
    private EmployeEntity employeEntity;
}
