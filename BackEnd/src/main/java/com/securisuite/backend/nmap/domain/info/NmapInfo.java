package com.securisuite.backend.nmap.domain.info;

import com.securisuite.backend.nmap.domain.Nmap;
import com.securisuite.backend.nmap.domain.NmapOption;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class NmapInfo {
    private NmapOption option;
    private String ip;
    private String port;
    private String cmd;
    private String regDts;
    private String logName;
    private String log;
    private String time;
    private HashMap<Integer, NmapResult> result;

    public NmapInfo(Nmap nmap, String log) {
        this.option = nmap.getOption();
        this.ip = nmap.getIp();
        this.port = nmap.getPort();
        this.cmd = "";
        for (String c : nmap.getTransCmd()) this.cmd += c + " ";
        this.regDts = nmap.getRegDts();
        this.logName = nmap.getLogName();
        this.log = log;
        this.time = getInfoTime();
        this.result = getInfoResult();
    }

    private String getInfoTime() {
        Pattern pattern = Pattern.compile("scanned in ([\\d.]+) seconds");
        Matcher matcher = pattern.matcher(this.log);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Empty";
        }
    }

    private HashMap<Integer, NmapResult> getInfoResult() {
        HashMap<Integer, NmapResult> rs = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\d+)/tcp\\s+(\\w+)\\s+(\\w+)");
        Matcher matcher = pattern.matcher(this.log);
        int cnt = 1;
        while (matcher.find()) {
            String port = matcher.group(1);
            String state = matcher.group(2);
            String service = matcher.group(3);
            rs.put(cnt, new NmapResult(port, state, service));
            cnt++;
        }
        return rs;
    }
}