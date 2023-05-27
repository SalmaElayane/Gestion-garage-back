package com.cigma.gg.controllers.bachoffice.dto;


import com.cigma.gg.entity.backoffice.EmployeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeDto implements Serializable{
    private long id ;
    private String nom;
    private String prenom;
    private long age;
    private String cin;
    private String tele;
    private String role;
    private String login;
    private String mot_de_passe;
    /**
     * @param employeEntity
     * @return
     */
    public static EmployeDto entityToDto(EmployeEntity employeEntity) {
        EmployeDto employeDto = new EmployeDto();
        if (employeEntity != null) {
            employeDto.setId(employeEntity.getId());
            employeDto.setNom(employeEntity.getNom());
            employeDto.setPrenom(employeEntity.getPrenom());
            employeDto.setAge(employeEntity.getAge());
            employeDto.setCin(employeEntity.getCin());
            employeDto.setTele(employeEntity.getTele());
            employeDto.setRole(employeEntity.getRole());
            employeDto.setLogin(employeEntity.getLogin());
            employeDto.setMot_de_passe(employeEntity.getMot_de_passe());
        }
        return employeDto;
    }

    /**
     * @param employeDto
     * @return
     */
    public static EmployeEntity dtoToEntity(EmployeDto employeDto) {
        EmployeEntity employeEntity = new EmployeEntity();
        if (employeDto != null) {
            employeEntity.setId(employeDto.getId());
            employeEntity.setNom(employeDto.getNom());
            employeEntity.setPrenom(employeDto.getPrenom());
            employeEntity.setAge(employeDto.getAge());
            employeEntity.setCin(employeDto.getCin());
            employeEntity.setTele(employeDto.getTele());
            employeEntity.setRole(employeDto.getRole());
            employeEntity.setLogin(employeDto.getLogin());
            employeEntity.setMot_de_passe(employeDto.getMot_de_passe());
        }
        return employeEntity;
    }

    /**
     * @param employeEntities
     * @return
     */
    public static List<EmployeDto> entitiesToDtos(List<EmployeEntity> employeEntities) {
        List<EmployeDto> employeDtos = new ArrayList<>();
        if (!employeEntities.isEmpty()) {
            employeDtos = employeEntities.stream().map(EmployeDto::entityToDto).collect(Collectors.toList());
        }
        return employeDtos;
    }
}
