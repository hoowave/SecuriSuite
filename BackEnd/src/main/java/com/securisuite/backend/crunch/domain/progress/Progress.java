package com.securisuite.backend.crunch.domain.progress;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class Progress {
    @NotEmpty(message = "일시는 필수값입니다.")
    private String regDts;
    private String[] transCmd;
    private String outputLogName;
    private String outputProgressName;

    @Builder
    public Progress(String regDts) {
        this.regDts = regDts;
        this.outputLogName = regDts + "_crunch_output.txt";
        this.outputProgressName = regDts + "_crunch_output_progress.txt";
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";
        transCmd[2] = "cat /var/www/html/download/logs/" + outputLogName + " |tail -1";
    }
}