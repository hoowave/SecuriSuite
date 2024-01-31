package com.securisuite.backend.report.domain;

import com.securisuite.backend.report.domain.info.DashBoardInfo;
import com.securisuite.backend.report.domain.info.FileListInfo;
import com.securisuite.backend.report.domain.info.LogListInfo;

public interface ReportService {
    DashBoardInfo dashBoard();

    FileListInfo fileList();

    LogListInfo logList();
}
