package com.securisuite.backend.system.domain.process.terminate.info;

import com.securisuite.backend.system.domain.process.terminate.Terminate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TerminateInfo {
    private String processName;
    private String pid;
    private String cmd;
    private String regDts;

    public TerminateInfo(Terminate terminate) {
        this.processName = terminate.getProcessName();
        this.pid = terminate.getPid();
        this.cmd = "";
        for (String c : terminate.getTransCmd()) this.cmd += c + " ";
        this.regDts = terminate.getRegDts();
    }
}
