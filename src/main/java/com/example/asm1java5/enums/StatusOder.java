package com.example.asm1java5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOder {
    PENDING(0),
    SUCCESS(1),
    CANCEL(2);

    private final long value;
}
