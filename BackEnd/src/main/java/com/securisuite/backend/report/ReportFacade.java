package com.securisuite.backend.report;

import com.securisuite.backend.report.domain.ReportService;
import com.securisuite.backend.report.domain.info.DashBoardInfo;
import com.securisuite.backend.report.domain.info.FileListInfo;
import com.securisuite.backend.report.domain.info.LogListInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportFacade {
    private final ReportService reportService;

    public DashBoardInfo dashBoard() {
        var dashBoardInfo = reportService.dashBoard();
        return dashBoardInfo;
    }

    public FileListInfo fileList() {
        var fileListInfo = reportService.fileList();
        return fileListInfo;
    }

    public List<LogListInfo> logList() {
        var logListInfo = reportService.logList();
        return logListInfo;
    }
}
