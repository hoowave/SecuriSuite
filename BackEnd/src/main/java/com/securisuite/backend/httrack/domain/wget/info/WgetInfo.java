package com.securisuite.backend.httrack.domain.wget.info;

import com.securisuite.backend.httrack.domain.wget.Wget;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WgetInfo {
    private String url;
    private String transUrl;
    private String cmd;
    private String regDts;
    private String logName;
    private String fileName;

    public WgetInfo(Wget wget) {
        this.url = wget.getUrl();
        this.transUrl = wget.getTransUrl();
        this.cmd = "";
        for (String c : wget.getTransCmd()) this.cmd += c + " ";
        this.regDts = wget.getRegDts();
        this.logName = wget.getLogName();
        this.fileName = transUrl + ".zip";
    }
}
