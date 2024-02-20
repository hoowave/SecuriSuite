package com.securisuite.frontend.web.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class LogListResponse {
    private String result;
    private Data data;
    private String message;
    private char complete;

    @Getter
    @ToString
    public static class Data {
        List<LogListInfo> logList;
    }

    @Getter
    @ToString
    public static class LogListInfo {
        private String type;
        private String logName;
        private String regDts;
        private char complete;
    }
}
