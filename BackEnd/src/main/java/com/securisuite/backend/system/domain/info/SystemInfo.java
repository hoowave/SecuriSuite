package com.securisuite.backend.system.domain.info;

import com.securisuite.backend.system.domain.System;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SystemInfo {
    private String cmd;
    private String regDts;
    private String logName;
    private String result;

    public SystemInfo(System system, String log) {
        this.cmd = "";
        for (String c : system.getTransCmd()) this.cmd += c + " ";
        this.regDts = system.getRegDts();
        this.logName = system.getLogName();
        this.result = log;
    }
}