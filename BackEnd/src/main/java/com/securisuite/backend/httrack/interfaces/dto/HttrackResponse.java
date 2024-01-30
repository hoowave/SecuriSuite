package com.securisuite.backend.httrack.interfaces.dto;


import com.securisuite.backend.httrack.domain.info.HttrackInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HttrackResponse {
    private String url;
    private String transUrl;
    private String cmd;
    private String regDts;
    private String logName;
    private String tempLog;
    private String fileName;

    public HttrackResponse(HttrackInfo httrackInfo) {
        this.url = httrackInfo.getUrl();
        this.transUrl = httrackInfo.getTransUrl();
        this.cmd = httrackInfo.getCmd();
        this.regDts = httrackInfo.getRegDts();
        this.logName = httrackInfo.getLogName();
        this.tempLog = httrackInfo.getTempLog();
        this.fileName = httrackInfo.getFileName();
    }
}
