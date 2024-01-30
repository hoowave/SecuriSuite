package com.securisuite.backend.nmap.interfaces.dto;

import com.securisuite.backend.nmap.domain.NmapOption;
import com.securisuite.backend.nmap.domain.command.NmapCmd;
import com.securisuite.backend.nmap.domain.info.NmapInfo;
import com.securisuite.backend.nmap.domain.info.NmapResult;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

public class NmapDto {

    @Getter
    @Setter
    @ToString
    public static class NmapRequest {
        private NmapOption option;
        @NotEmpty(message = "IP는 필수값입니다.")
        private String ip;
        private String port;

        public NmapCmd toCommand() {
            return NmapCmd.builder()
                    .option(option)
                    .ip(ip)
                    .port(port)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class NmapResponse {
        private NmapOption option;
        private String ip;
        private String port;
        private String cmd;
        private String regDts;
        private String logName;
        private String log;
        private String time;
        private HashMap<Integer, NmapResult> result;

        public NmapResponse(NmapInfo nmapInfo) {
            this.option = nmapInfo.getOption();
            this.ip = nmapInfo.getIp();
            this.port = nmapInfo.getPort();
            this.cmd = nmapInfo.getCmd();
            this.regDts = nmapInfo.getRegDts();
            this.logName = nmapInfo.getLogName();
            this.log = nmapInfo.getLog();
            this.time = nmapInfo.getTime();
            this.result = nmapInfo.getResult();
        }
    }

}
