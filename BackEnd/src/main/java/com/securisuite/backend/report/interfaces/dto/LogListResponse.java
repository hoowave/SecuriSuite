package com.securisuite.backend.report.interfaces.dto;

import com.securisuite.backend.report.domain.info.LogListInfo;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class LogListResponse {
    List<LogListInfo> logList;

    public LogListResponse(List<LogListInfo> logList){
        this.logList = logList;
    }
}
