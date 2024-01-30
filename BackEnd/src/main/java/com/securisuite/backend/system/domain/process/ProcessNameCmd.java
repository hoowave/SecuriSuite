package com.securisuite.backend.system.domain.process;

import com.securisuite.backend.system.domain.process.pid.Pid;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProcessNameCmd {
    private String processName;

    public Pid toEntity() {
        return Pid.builder()
                .processName(processName)
                .build();
    }
}
