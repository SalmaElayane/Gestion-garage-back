package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.FactureEntity;
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
public class FactureReferenceDto implements Serializable {
    private Long id;
    private String refrence;

    /**
     * @param factureDto
     * @return
     */
    public static FactureReferenceDto getReference(FactureDto factureDto) {
        FactureReferenceDto factureReferenceDto= new FactureReferenceDto();
        if (factureDto != null) {
            factureDto.setId(factureDto.getId());
            factureDto.setReference(factureDto.getReference());
        }
        return factureReferenceDto;
    }

    /**
     * @param factureDtos
     * @return
     */
    public static List<FactureReferenceDto> getListReference(List<FactureDto> factureDtos) {
        List<FactureReferenceDto> factureReferenceDtos = new ArrayList<>();
        if (!factureDtos.isEmpty()) {
            factureDtos.forEach(v -> factureReferenceDtos.add(FactureReferenceDto.getReference(v)));
        }
        return factureReferenceDtos;
    }
}
