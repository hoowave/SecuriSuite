package com.securisuite.frontend.web.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class FileListResponse {
    private String result;
    private Data data;
    private String message;

    @Getter
    @ToString
    public static class Data {
        private Map<Integer, HttrackFileInfo> httrackFileInfos;
        Map<Integer, CrunchFileInfo> crunchFileInfos;
    }

    @Getter
    @ToString
    public static class HttrackFileInfo {
        private String httrackRegDts;
        private String httrackTransUrl;
        private String httrackUrl;
    }

    @Getter
    @ToString
    public static class CrunchFileInfo {
        private int crunchMinWord;
        private int crunchMaxWord;
        private String crunchWords;
        private String crunchRegDts;
        private String crunchLogName;
    }
}
