package com.securisuite.backend.httrack.interfaces.dto;

import com.securisuite.backend.httrack.domain.completion.command.CompletionCmd;
import com.securisuite.backend.httrack.domain.completion.info.CompletionInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CompletionDto {
    @Getter
    @Setter
    @ToString
    public static class CompletionRequest {
        @NotEmpty(message = "URL은 필수값입니다.")
        private String url;
        @NotEmpty(message = "일시는 필수값입니다.")
        private String regDts;

        public CompletionCmd toCommand() {
            return CompletionCmd.builder()
                    .url(url)
                    .regDts(regDts)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class CompletionResponse {
        private String url;
        private String regDts;
        private String transUrl;
        private String fileName;
        private String logName;
        private String tempLog;
        private boolean complete;
        private String message;

        public CompletionResponse(CompletionInfo completionInfo) {
            this.url = completionInfo.getUrl();
            this.regDts = completionInfo.getRegDts();
            this.transUrl = completionInfo.getTransUrl();
            this.fileName = completionInfo.getFileName();
            this.logName = completionInfo.getLogName();
            this.tempLog = completionInfo.getTempLog();
            this.complete = completionInfo.isComplete();
            this.message = completionInfo.getMessage();
        }
    }
}
