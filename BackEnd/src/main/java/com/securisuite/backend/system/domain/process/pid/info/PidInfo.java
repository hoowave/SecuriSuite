package com.securisuite.backend.system.domain.process.pid.info;

import com.securisuite.backend.system.domain.process.pid.Pid;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class PidInfo {
    private String processName;
    private String cmd;
    private String regDts;
    private String logName;
    private String log;
    private String pid;

    public PidInfo(Pid pid, String log) {
        this.processName = pid.getProcessName();
        this.cmd = "";
        for (String c : pid.getTransCmd()) this.cmd += c + " ";
        this.regDts = pid.getRegDts();
        this.logName = pid.getLogName();
        this.log = log;
        this.pid = getPidInfo();
    }

    private String getPidInfo() {
        Pattern pattern = Pattern.compile("\\S+\\s+(\\d+)");
        Matcher matcher = pattern.matcher(this.log);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
