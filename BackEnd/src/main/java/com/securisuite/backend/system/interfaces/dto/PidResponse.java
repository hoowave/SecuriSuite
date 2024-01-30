package com.securisuite.backend.system.interfaces.dto;

import com.securisuite.backend.system.domain.process.pid.info.PidInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PidResponse {
    private String processName;
    private String cmd;
    private String regDts;
    private String logName;
    private String log;
    private String pid;

    public PidResponse(PidInfo pidInfo) {
        this.processName = pidInfo.getProcessName();
        this.cmd = pidInfo.getCmd();
        this.regDts = pidInfo.getRegDts();
        this.logName = pidInfo.getLogName();
        this.log = pidInfo.getLog();
        this.pid = pidInfo.getPid();
    }
}
