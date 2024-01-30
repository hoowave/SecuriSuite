package com.securisuite.backend.johntheripper.interfaces.dto;

import com.securisuite.backend.johntheripper.domain.completion.command.CompletionCmd;
import com.securisuite.backend.johntheripper.domain.completion.info.CompletionInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CompletionDto {
    @Getter
    @Setter
    @ToString
    public static class CompletionRequest {
        @NotEmpty(message = "일시는 필수값입니다.")
        private String regDts;

        public CompletionCmd toCommand() {
            return CompletionCmd.builder()
                    .regDts(regDts)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class CompletionResponse {
        private String regDts;
        private String logName;
        private String hashName;
        private boolean complete;
        private String message;
        private String password;

        public CompletionResponse(CompletionInfo completionInfo) {
            this.regDts = completionInfo.getRegDts();
            this.logName = completionInfo.getLogName();
            this.hashName = completionInfo.getHashName();
            this.complete = completionInfo.isComplete();
            this.message = completionInfo.getMessage();
            this.password = completionInfo.getPassword();
        }
    }
}
