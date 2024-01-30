package com.securisuite.backend.nmap.domain.command;

import com.securisuite.backend.nmap.domain.Nmap;
import com.securisuite.backend.nmap.domain.NmapOption;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class NmapCmd {
    private NmapOption option;
    private String ip;
    private String port;

    public Nmap toEntity() {
        return Nmap.builder()
                .option(option)
                .ip(ip)
                .port(port)
                .build();
    }
}
