package com.securisuite.backend.httrack.domain.completion.info;

import com.securisuite.backend.httrack.domain.completion.Completion;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompletionInfo {
    private String url;
    private String regDts;
    private String transUrl;
    private String fileName;
    private String logName;
    private String tempLog;
    private boolean complete;
    private String message;

    public CompletionInfo(Completion completion, boolean complete, String message) {
        this.url = completion.getUrl();
        this.regDts = completion.getRegDts();
        this.transUrl = completion.getTransUrl();
        this.fileName = completion.getFileName();
        this.logName = completion.getLogName();
        this.tempLog = completion.getTempLog();
        this.complete = complete;
        this.message = message;
    }
}
