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
public class RendezVousRefDto implements Serializable {
    private Long id;
    private String reference;

    /**
     * @param rendezVousDto
     * @return
     */
    public static RendezVousRefDto getReference(RendezVousDto rendezVousDto) {
        RendezVousRefDto rendezVousRefDto = new RendezVousRefDto();
        if (rendezVousDto != null) {
            rendezVousRefDto.setId(rendezVousDto.getId());
            rendezVousRefDto.setReference(rendezVousDto.getReference());
        }
        return rendezVousRefDto;
    }

    /**
     * @param rendezVousDtos
     * @return
     */
    public static List<RendezVousRefDto> getListReference(List<RendezVousDto> rendezVousDtos) {
        List<RendezVousRefDto> rendezVousRefDtos = new ArrayList<>();
        if (!rendezVousDtos.isEmpty()) {
            rendezVousDtos.forEach(v -> rendezVousRefDtos.add(RendezVousRefDto.getReference(v)));
        }
        return rendezVousRefDtos;
    }
}
