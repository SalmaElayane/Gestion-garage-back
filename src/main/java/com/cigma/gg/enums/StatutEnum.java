package com.cigma.gg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
@Getter
public enum StatutEnum {
    VALIDEE("VALIDEE"),
    REFUSEE("REFUSEE"),
    EN_ATTENDANT("En attendant");

    private final String code;
}
