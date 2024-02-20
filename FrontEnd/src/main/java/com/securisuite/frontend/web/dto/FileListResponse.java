package com.securisuite.frontend.web.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
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
        private List<HttrackFileInfo> httrackFileInfos;
        private Map<Integer, CrunchFileInfo> crunchFileInfos;
    }

    @Getter
    @ToString
    public static class HttrackFileInfo {
        private String httrackRegDts;
        private String httrackTransUrl;
        private String httrackUrl;
        private String httrackType;
        private char httrackComplete;
    }

    @Getter
    @ToString
    public static class CrunchFileInfo {
        private int crunchMinWord;
        private int crunchMaxWord;
        private String crunchWords;
        private String crunchRegDts;
        private String crunchLogName;
        private char crunchComplete;
    }
}
