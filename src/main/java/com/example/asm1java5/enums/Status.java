package com.example.asm1java5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE(1)
    ,NO_ACTIVE(0);

    private final int value;

}
