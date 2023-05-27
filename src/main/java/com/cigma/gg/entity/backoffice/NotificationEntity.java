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
@Table(name = "notification")
@Entity
public class NotificationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Les attributs

    private Long id;
    @Column
    private String reference;
    private Date dateNotification;
    private String objet;
    private String message;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "email_utilisateur", referencedColumnName = "email")
    private UtilisateurEntity utilisateurEntity;


}
