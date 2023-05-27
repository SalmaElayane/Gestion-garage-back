package com.cigma.gg.entity.backoffice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pirmission")
@Entity
public class PirmissionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Les attributs
    private Long id_permission;

    @Column
    private String libelle;

    @OneToMany(mappedBy = "pirmissionEntity", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<RolePermissionEntity> rolePermissionEntityList;
}
