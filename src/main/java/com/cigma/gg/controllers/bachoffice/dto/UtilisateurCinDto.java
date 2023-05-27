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
public class UtilisateurCinDto implements Serializable {
    private Long id;
    private String cin;

    /**
     * @param utilisateurDto
     * @return
     */
    public static UtilisateurCinDto getCin(UtilisateurDto utilisateurDto) {
        UtilisateurCinDto utilisateurCinDto = new UtilisateurCinDto();
        if (utilisateurDto != null) {
            utilisateurCinDto.setId(utilisateurDto.getId());
            utilisateurCinDto.setCin(utilisateurDto.getCin());
        }
        return utilisateurCinDto;
    }

    /**
     * @param utilisateurDtos
     * @return
     */
    public static List<UtilisateurCinDto> getListCin(List<UtilisateurDto> utilisateurDtos) {
        List<UtilisateurCinDto> utilisateurCinDtos = new ArrayList<>();
        if (!utilisateurDtos.isEmpty()) {
            utilisateurDtos.forEach(v -> utilisateurCinDtos.add(UtilisateurCinDto.getCin(v)));
        }
        return utilisateurCinDtos;
    }
}
