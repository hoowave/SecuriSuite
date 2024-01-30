package com.securisuite.backend.httrack.domain.info;

import com.securisuite.backend.httrack.domain.Httrack;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HttrackInfo {
    private String url;
    private String transUrl;
    private String cmd;
    private String regDts;
    private String logName;
    private String tempLog;
    private String fileName;

    public HttrackInfo(Httrack httrack) {
        this.url = httrack.getUrl();
        this.transUrl = httrack.getTransUrl();
        this.cmd = "";
        for (String c : httrack.getTransCmd()) this.cmd += c + " ";
        this.regDts = httrack.getRegDts();
        this.logName = httrack.getLogName();
        this.tempLog = httrack.getTempLog();
        this.fileName = transUrl + ".zip";
    }
}
