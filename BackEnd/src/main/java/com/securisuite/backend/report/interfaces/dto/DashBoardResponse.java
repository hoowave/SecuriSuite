package com.securisuite.backend.report.interfaces.dto;

import com.securisuite.backend.report.domain.info.DashBoardInfo;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class DashBoardResponse {
    Map<String, Long> counts;

    public DashBoardResponse(DashBoardInfo dashBoardInfo) {
        this.counts = dashBoardInfo.getCounts();
    }
}
