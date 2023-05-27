package com.cigma.gg.controllers.bachoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EmployeCinDto implements Serializable {
    private Long id;
    private String cin;

    /**
     * @param employeDto
     * @return
     */
    public static EmployeCinDto getCin(EmployeDto employeDto) {
        EmployeCinDto employeCinDto = new EmployeCinDto();
        if (employeDto != null) {
            employeCinDto.setId(employeDto.getId());
            employeCinDto.setCin(employeDto.getCin());
        }
        return employeCinDto;
    }

    /**
     * @param employeDtos
     * @return
     */
    public static List<EmployeCinDto> getListCin(List<EmployeDto> employeDtos) {
        List<EmployeCinDto> employeCinDtos = new ArrayList<>();
        if (!employeDtos.isEmpty()) {
            employeDtos.forEach(v -> employeCinDtos.add(EmployeCinDto.getCin(v)));
        }
        return employeCinDtos;
    }
}
