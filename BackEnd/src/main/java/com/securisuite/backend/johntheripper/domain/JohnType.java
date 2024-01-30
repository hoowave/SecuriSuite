package com.securisuite.backend.johntheripper.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JohnType {
    ARCHIVE("zip2john"),
    OFFICE("office2john");

    private final String type;

    public String getType() {
        return type;
    }
}
