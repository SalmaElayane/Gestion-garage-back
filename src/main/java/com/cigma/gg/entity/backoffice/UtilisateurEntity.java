package com.cigma.gg.entity.backoffice;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur")
@Entity
public class UtilisateurEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Les attributs

    private Long id;
    @Column
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String tele;
    private String role;
    private String login;
    private String mot_de_passe;
    @OneToMany(mappedBy = "utilisateurEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<VoitureEntity> voitureEntitylist;

    @OneToMany(mappedBy = "utilisateurEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<RendezVousEntity> rendezVousEntityList;

    @OneToMany(mappedBy = "utilisateurEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<NotificationEntity> notificationEntitylist;

}
