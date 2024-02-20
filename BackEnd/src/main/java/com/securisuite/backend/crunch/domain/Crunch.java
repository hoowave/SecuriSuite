package com.securisuite.backend.crunch.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Crunch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1, message = "최소값은 1 이상이어야 합니다.")
    @Max(value = 12, message = "최대값은 12 이하여야 합니다.")
    private int minWord;
    @Min(value = 1, message = "최소값은 1 이상이어야 합니다.")
    @Max(value = 12, message = "최대값은 12 이하여야 합니다.")
    private int maxWord;
    @NotEmpty(message = "문자열은 필수값입니다.")
    private String words;
    @Transient
    private String[] transCmd;
    private String regDts;
    private String logName;
    private String outputLogName;
    private char complete;

    @Builder
    public Crunch(int minWord, int maxWord, String words) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_crunch.txt";
        this.minWord = minWord;
        this.maxWord = maxWord;
        this.words = words;
        this.outputLogName = this.regDts + "_crunch_output.txt";
        this.complete = 'N';
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";
        transCmd[2] = "crunch " + minWord + " " + maxWord + " " + words + " -o " + "/var/www/html/download/files/" + this.logName + " > /var/www/html/download/logs/" + this.outputLogName + " 2>&1 &";
    }

    public void Complete() {
        this.complete = 'Y';
    }
}
