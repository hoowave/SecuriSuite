package com.securisuite.backend.report.domain.info;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class FileListInfo {
    Map<Integer, HttrackFileInfo> httrackFileInfos;
    Map<Integer, CrunchFileInfo> crunchFileInfos;

    public FileListInfo(Map<Integer, HttrackFileInfo> httrackFileInfoMap, Map<Integer, CrunchFileInfo> crunchFileInfoMap) {
        this.httrackFileInfos = httrackFileInfoMap;
        this.crunchFileInfos = crunchFileInfoMap;
    }
}
