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
public class NotificationRefDto implements Serializable {
    private Long id;
    private String refrence;

    /**
     * @param notificationDto
     * @return
     */
    public static NotificationRefDto getReference(NotificationDto notificationDto) {
        NotificationRefDto notificationRefDto= new NotificationRefDto();
        if (notificationDto != null) {
            notificationDto.setId(notificationDto.getId());
            notificationDto.setReference(notificationDto.getReference());
        }
        return notificationRefDto;
    }

    /**
     * @param notificationDtos
     * @return
     */
    public static List<NotificationRefDto> getListReference(List<NotificationDto> notificationDtos) {
        List<NotificationRefDto> notificationRefDtos = new ArrayList<>();
        if (!notificationDtos.isEmpty()) {
            notificationDtos.forEach(v -> notificationRefDtos.add(NotificationRefDto.getReference(v)));
        }
        return notificationRefDtos;
    }
}
