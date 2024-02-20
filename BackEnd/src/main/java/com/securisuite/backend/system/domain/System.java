package com.securisuite.backend.system.domain;

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
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "명령어는 필수값입니다.")
    private String cmd;
    @Transient
    private String[] transCmd;
    private String regDts;
    private String logName;
    private char complete;

    @Builder
    public System(String cmd) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_system.txt";
        this.cmd = cmd;
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";
        transCmd[2] = cmd;
    }

    public void Complete() {
        this.complete = 'Y';
    }

}