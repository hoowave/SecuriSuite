package com.securisuite.backend.report.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@Builder
public class CrunchFileInfo implements Comparable<CrunchFileInfo>{
    private int crunchMinWord;
    private int crunchMaxWord;
    private String crunchWords;
    private String crunchRegDts;
    private String crunchLogName;
    private char crunchComplete;

    @Override
    public int compareTo(CrunchFileInfo other) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime thisDate = LocalDateTime.parse(this.crunchRegDts, formatter);
        LocalDateTime otherDate = LocalDateTime.parse(other.crunchRegDts, formatter);
        return otherDate.compareTo(thisDate);
    }
}
