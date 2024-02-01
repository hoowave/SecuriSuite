package com.securisuite.backend.report.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class HttrackFileInfo {
    private String httrackRegDts;
    private String httrackTransUrl;
    private String httrackUrl;
}