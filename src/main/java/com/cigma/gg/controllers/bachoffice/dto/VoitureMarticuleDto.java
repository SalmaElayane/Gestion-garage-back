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
public class VoitureMarticuleDto implements Serializable {
    private Long id;
    private String matricule;

    /**
     * @param voitureDto
     * @return
     */
    public static VoitureMarticuleDto getMarticule(VoitureDto voitureDto) {
        VoitureMarticuleDto voitureMarticuleDto = new VoitureMarticuleDto();
        if (voitureDto != null) {
            voitureMarticuleDto.setId(voitureDto.getId());
            voitureMarticuleDto.setMatricule(voitureDto.getMatricule());
        }
        return voitureMarticuleDto;
    }

    /**
     * @param voitureDtos
     * @return
     */
    public static List<VoitureMarticuleDto> getListMarticule(List<VoitureDto> voitureDtos) {
        List<VoitureMarticuleDto> voitureMarticuleDtos = new ArrayList<>();
        if (!voitureDtos.isEmpty()) {
            voitureDtos.forEach(v -> voitureMarticuleDtos.add(VoitureMarticuleDto.getMarticule(v)));
        }
        return voitureMarticuleDtos;
    }
}
