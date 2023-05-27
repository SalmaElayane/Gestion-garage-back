package com.cigma.gg.entity.backoffice;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnostic")
@Entity
public class DiagnosticEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Les attributs

    private long id ;
    @Column
    private String reference;
    private String libelle;
    private Double prix;
    private long duree;



}
