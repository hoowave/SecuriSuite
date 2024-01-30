package com.securisuite.backend.system.domain.command;

import com.securisuite.backend.system.domain.System;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SystemCmd {
    private String cmd;

    public System toEntity(){
        return System.builder()
                .cmd(cmd)
                .build();
    }
}
