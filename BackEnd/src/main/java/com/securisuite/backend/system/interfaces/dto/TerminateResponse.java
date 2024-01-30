package com.securisuite.backend.system.interfaces.dto;

import com.securisuite.backend.system.domain.process.terminate.info.TerminateInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TerminateResponse {
    private String processName;
    private String pid;
    private String cmd;
    private String regDts;

    public TerminateResponse(TerminateInfo terminateInfo) {
        this.processName = terminateInfo.getProcessName();
        this.pid = terminateInfo.getPid();
        this.cmd = terminateInfo.getCmd();
        this.regDts = terminateInfo.getRegDts();
    }
}
