package com.securisuite.backend.report.interfaces.dto;

import com.securisuite.backend.report.domain.info.CrunchFileInfo;
import com.securisuite.backend.report.domain.info.FileListInfo;
import com.securisuite.backend.report.domain.info.HttrackFileInfo;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
public class FileListResponse {
    List<HttrackFileInfo> httrackFileInfos;
    Map<Integer, CrunchFileInfo> crunchFileInfos;

    public FileListResponse(FileListInfo fileListInfo){
        this.httrackFileInfos = fileListInfo.getHttrackFileInfos();
        this.crunchFileInfos = fileListInfo.getCrunchFileInfos();
    }
}
