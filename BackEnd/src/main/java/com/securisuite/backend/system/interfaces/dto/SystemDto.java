package com.securisuite.backend.system.interfaces.dto;

import com.securisuite.backend.system.domain.command.SystemCmd;
import com.securisuite.backend.system.domain.info.SystemInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SystemDto {

    @Getter
    @Setter
    @ToString
    public static class SystemRequest {
        @NotEmpty(message = "명령어는 필수값입니다.")
        private String cmd;

        public SystemCmd toCommand() {
            return SystemCmd.builder()
                    .cmd(cmd)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class SystemResponse {
        private String cmd;
        private String regDts;
        private String logName;
        private String result;

        public SystemResponse(SystemInfo systemInfo) {
            this.cmd = systemInfo.getCmd();
            this.regDts = systemInfo.getRegDts();
            this.logName = systemInfo.getLogName();
            this.result = systemInfo.getResult();
        }

    }
}
