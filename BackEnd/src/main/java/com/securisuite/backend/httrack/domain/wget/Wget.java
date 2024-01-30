package com.securisuite.backend.httrack.domain.wget;

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
public class Wget {
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

    @Builder
    public Wget(String url) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_wget.txt";
        this.url = url;
        this.transUrl = regDts + "_" + url.replaceAll("[:/.]+", "_");
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";
        transCmd[2] = "mkdir /var/www/html/download/files/" + transUrl + " && " +
                "wget -P /var/www/html/download/files/" + transUrl + " -m " + url + " > /var/www/html/download/logs/" + logName + " 2>&1 && " +
                "zip -r /var/www/html/download/files/" + transUrl + ".zip /var/www/html/download/files/" + transUrl + " && " +
                "rm -rf /var/www/html/download/files/" + transUrl;
    }
}
