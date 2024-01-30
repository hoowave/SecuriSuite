package com.securisuite.backend.crunch.interfaces.dto;

import com.securisuite.backend.crunch.domain.progress.command.ProgressCmd;
import com.securisuite.backend.crunch.domain.progress.info.ProgressInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProgressDto {

    @Getter
    @Setter
    @ToString
    public static class ProgressRequest {
        @NotEmpty(message = "일시는 필수값입니다.")
        private String regDts;

        public ProgressCmd toCommand() {
            return ProgressCmd.builder()
                    .regDts(regDts)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class ProgressResponse {
        private String regDts;
        private String log;
        private String logName;
        private String percent;

        public ProgressResponse(ProgressInfo progressInfo) {
            this.regDts = progressInfo.getRegDts();
            this.log = progressInfo.getLog();
            this.logName = progressInfo.getLogName();
            this.percent = progressInfo.getPercent();
        }
    }
}
