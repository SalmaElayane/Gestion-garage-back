package com.cigma.gg.controllers.authentication.dto;

import java.io.Serializable;

public record JwtRequestDto(String username, String password) implements Serializable {

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private static final long serialVersionUID = 5926468583005150707L;

}
