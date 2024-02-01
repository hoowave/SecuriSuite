package com.securisuite.backend.report.domain;

import com.securisuite.backend.report.domain.info.DashBoardInfo;
import com.securisuite.backend.report.domain.info.FileListInfo;
import com.securisuite.backend.report.domain.info.LogListInfo;

import java.util.List;

public interface ReportService {
    DashBoardInfo dashBoard();

    FileListInfo fileList();

    List<LogListInfo> logList();
}
