package com.securisuite.backend.crunch.interfaces.dto;

import com.securisuite.backend.crunch.domain.command.CrunchCmd;
import com.securisuite.backend.crunch.domain.info.CrunchInfo;
import com.securisuite.backend.crunch.domain.info.CrunchResult;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CrunchDto {

    @Getter
    @Setter
    @ToString
    public static class CrunchRequest {
        @Min(value = 1, message = "최소값은 1 이상이어야 합니다.")
        @Max(value = 12, message = "최대값은 12 이하여야 합니다.")
        private int minWord;
        @Min(value = 1, message = "최소값은 1 이상이어야 합니다.")
        @Max(value = 12, message = "최대값은 12 이하여야 합니다.")
        private int maxWord;
        @Size(min = 1, message = "문자열은 필수값입니다.")
        private String words;

        public CrunchCmd toCommand() {
            return CrunchCmd.builder()
                    .minWord(minWord)
                    .maxWord(maxWord)
                    .words(words)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class CrunchResponse {
        private int minWord;
        private int maxWord;
        private String words;
        private String cmd;
        private String regDts;
        private String logName;
        private String log;
        private CrunchResult crunchResult;

        public CrunchResponse(CrunchInfo crunchInfo) {
            this.minWord = crunchInfo.getMinWord();
            this.maxWord = crunchInfo.getMaxWord();
            this.words = crunchInfo.getWords();
            this.cmd = crunchInfo.getCmd();
            this.regDts = crunchInfo.getRegDts();
            this.logName = crunchInfo.getLogName();
            this.log = crunchInfo.getLog();
            this.crunchResult = crunchInfo.getCrunchResult();
        }
    }
}
