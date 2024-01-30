package com.securisuite.backend.nmap.domain.info;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NmapResult {
    private String port;
    private String state;
    private String service;

    public NmapResult(String port, String state, String service) {
        this.port = port;
        this.state = state;
        this.service = service;
    }
}
