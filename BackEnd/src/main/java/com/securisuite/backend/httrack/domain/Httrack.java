package com.securisuite.backend.httrack.domain;

import jakarta.persistence.*;
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
@Entity
public class Httrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "URL은 필수값입니다.")
    private String url;
    private String transUrl;
    @Transient
    private String[] transCmd;
    private String regDts;
    private String logName;
    private String tempLog;
    private char complete;

    @Builder
    public Httrack(String url) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_httrack.txt";
        this.tempLog = this.regDts + "_tempLog.txt";
        this.url = url;
        this.transUrl = regDts + "_" + url.replaceAll("[:/.]+", "_");
        this.complete = 'N';
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";
        transCmd[2] = "(" +
                "mkdir /var/www/html/download/files/" + transUrl + " && " +
                "httrack " + url + " -O /var/www/html/download/files/" + transUrl + " -%v > /var/www/html/download/logs/" + logName + " && " +
                "zip -r /var/www/html/download/files/" + transUrl + ".zip /var/www/html/download/files/" + transUrl + " && " +
                "rm -rf /var/www/html/download/files/" + transUrl +
                ") > /var/www/html/download/logs/" + tempLog + " 2>&1 &";
    }

    public void Complete() {
        this.complete = 'Y';
    }
}
