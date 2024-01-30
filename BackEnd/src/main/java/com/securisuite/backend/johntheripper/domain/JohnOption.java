package com.securisuite.backend.johntheripper.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JohnOption {
    DEFAULT("기본리스트사용"),
    CUSTOM("사용자리스트사용");

    private final String option;

    public String getOption() {
        return option;
    }
}
