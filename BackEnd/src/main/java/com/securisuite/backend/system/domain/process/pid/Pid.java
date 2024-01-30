package com.securisuite.backend.system.domain.process.pid;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class Pid {
    // Process <-> Pid
    @NotEmpty(message = "프로세스명은 필수값입니다.")
    private String processName;
    private String[] transCmd;
    private String regDts;
    private String logName;

    @Builder
    public Pid(String processName) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_pid.txt";
        this.processName = processName;
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";
        transCmd[2] = "ps aux | grep " + processName + " | grep -v grep";
    }
}
