package com.securisuite.backend.nmap.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NmapOption {
    ALL("-all"),
    SET("-p");

    private final String option;

    public String getOption() {
        return option;
    }
}
