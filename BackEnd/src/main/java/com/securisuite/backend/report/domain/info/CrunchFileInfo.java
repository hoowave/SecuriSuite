package com.securisuite.backend.report.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CrunchFileInfo {
    private int crunchMinWord;
    private int crunchMaxWord;
    private String crunchWords;
    private String crunchRegDts;
    private String crunchLogName;
}
