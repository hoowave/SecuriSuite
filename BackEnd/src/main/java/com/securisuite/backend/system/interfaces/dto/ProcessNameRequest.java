package com.securisuite.backend.system.interfaces.dto;

import com.securisuite.backend.system.domain.process.ProcessNameCmd;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessNameRequest {
    @NotEmpty(message = "프로세스명은 필수값입니다.")
    private String processName;

    public ProcessNameCmd toCommand() {
        return ProcessNameCmd.builder()
                .processName(processName)
                .build();
    }
}
