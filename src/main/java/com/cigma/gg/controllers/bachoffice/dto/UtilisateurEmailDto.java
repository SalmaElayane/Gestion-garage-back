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
public class UtilisateurEmailDto implements Serializable {
    private Long id;
    private String email;

    /**
     * @param utilisateurDto
     * @return
     */
    public static UtilisateurEmailDto getEmail(UtilisateurDto utilisateurDto) {
        UtilisateurEmailDto utilisateurEmailDto = new UtilisateurEmailDto();
        if (utilisateurDto != null) {
            utilisateurEmailDto.setId(utilisateurDto.getId());
            utilisateurEmailDto.setEmail(utilisateurDto.getEmail());
        }
        return utilisateurEmailDto;
    }

    /**
     * @param utilisateurDtos
     * @return
     */
    public static List<UtilisateurEmailDto> getListEmail(List<UtilisateurDto> utilisateurDtos) {
        List<UtilisateurEmailDto> utilisateurEmailDtos = new ArrayList<>();
        if (!utilisateurDtos.isEmpty()) {
            utilisateurDtos.forEach(v -> utilisateurEmailDtos.add(UtilisateurEmailDto.getEmail(v)));
        }
        return utilisateurEmailDtos;
    }
}
