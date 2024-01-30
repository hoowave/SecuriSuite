package com.securisuite.backend.httrack.domain.completion;

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
public class Completion {
    @NotEmpty(message = "URL은 필수값입니다.")
    private String url;
    @NotEmpty(message = "일시는 필수값입니다.")
    private String regDts;
    private String transUrl;
    private String fileName;
    private String logName;
    private String tempLog;

    @Builder
    public Completion(String url, String regDts) {
        this.url = url;
        this.regDts = regDts;
        this.transUrl = url.replaceAll("[:/.]+", "_");
        this.fileName = regDts + "_" + transUrl + ".zip";
        this.logName = this.regDts + "_httrack.txt";
        this.tempLog = this.regDts + "_tempLog.txt";
    }
}
