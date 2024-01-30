package com.securisuite.backend.httrack.interfaces.dto;

import com.securisuite.backend.httrack.domain.wget.info.WgetInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WgetResponse {
    private String url;
    private String transUrl;
    private String cmd;
    private String regDts;
    private String logName;
    private String fileName;

    public WgetResponse(WgetInfo wgetInfo){
        this.url = wgetInfo.getUrl();
        this.transUrl = wgetInfo.getTransUrl();
        this.cmd = wgetInfo.getCmd();
        this.regDts = wgetInfo.getRegDts();
        this.logName = wgetInfo.getLogName();
        this.fileName = wgetInfo.getFileName();
    }
}
