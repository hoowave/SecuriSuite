package com.securisuite.backend.report.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@Builder
public class HttrackFileInfo implements Comparable<HttrackFileInfo> {
    private String httrackRegDts;
    private String httrackTransUrl;
    private String httrackUrl;
    private String httrackType;
    private char httrackComplete;

    @Override
    public int compareTo(HttrackFileInfo other) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime thisDate = LocalDateTime.parse(this.httrackRegDts, formatter);
        LocalDateTime otherDate = LocalDateTime.parse(other.httrackRegDts, formatter);
        return otherDate.compareTo(thisDate);
    }
}