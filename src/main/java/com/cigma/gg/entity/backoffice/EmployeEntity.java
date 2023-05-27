package com.cigma.gg.entity.backoffice;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employe")
@Entity
public class EmployeEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Les attributs

    private long id ;
    @Column
    private String nom;
    private String prenom;
    private long age;
    private String cin;
    private String tele;
    private String role;
    private String login;
    private String mot_de_passe;
    @OneToMany(mappedBy = "employeEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<TacheEntity> tacheEntityList;


}
