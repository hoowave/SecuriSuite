package com.securisuite.backend.report.domain.info;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class LogListInfo implements Comparable<LogListInfo>{
    private String type;
    private String logName;
    private String regDts;
    private char complete;

    public LogListInfo(String type, String logName, String regDts, char complete) {
        this.type = type;
        this.logName = logName;
        this.regDts = regDts;
        this.complete = complete;
    }

    @Override
    public int compareTo(LogListInfo other) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime thisDate = LocalDateTime.parse(this.regDts, formatter);
        LocalDateTime otherDate = LocalDateTime.parse(other.regDts, formatter);
        return otherDate.compareTo(thisDate);
    }
}
