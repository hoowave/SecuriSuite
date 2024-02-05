package com.securisuite.backend.report.domain.info;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
public class FileListInfo {
    List<HttrackFileInfo> httrackFileInfos;
    Map<Integer, CrunchFileInfo> crunchFileInfos;

    public FileListInfo(List<HttrackFileInfo> httrackFileInfoList, Map<Integer, CrunchFileInfo> crunchFileInfoMap) {
        this.httrackFileInfos = httrackFileInfoList;
        this.crunchFileInfos = crunchFileInfoMap;
    }
}
