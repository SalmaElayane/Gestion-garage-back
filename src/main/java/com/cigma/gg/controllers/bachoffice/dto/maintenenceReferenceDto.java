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
public class maintenenceReferenceDto implements Serializable {
    private Long id;
    private String refrence;

    /**
     * @param maintenenceDto
     * @return
     */
    public static maintenenceReferenceDto getReference(MaintenenceDto maintenenceDto) {
        maintenenceReferenceDto maintenenceReferenceDto= new maintenenceReferenceDto();
        if (maintenenceDto != null) {
            maintenenceDto.setId(maintenenceDto.getId());
            maintenenceDto.setReference(maintenenceDto.getReference());
        }
        return maintenenceReferenceDto;
    }

    /**
     * @param maintenenceDtos
     * @return
     */
    public static List<maintenenceReferenceDto> getListReference(List<MaintenenceDto> maintenenceDtos) {
        List<maintenenceReferenceDto> maintenenceRefDtos = new ArrayList<>();
        if (!maintenenceDtos.isEmpty()) {
            maintenenceDtos.forEach(v -> maintenenceRefDtos.add(maintenenceReferenceDto.getReference(v)));
        }
        return maintenenceRefDtos;
    }
}
