package com.cigma.gg.controllers.authentication.dto;

import java.io.Serializable;

public record JwtResponseDto(String jwttoken) implements Serializable {

    public String getJwttoken() {
        return jwttoken;
    }

    private static final long serialVersionUID = -8091879091924046844L;

}
