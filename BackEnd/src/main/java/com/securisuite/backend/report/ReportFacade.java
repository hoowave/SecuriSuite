package com.securisuite.backend.report;

import com.securisuite.backend.report.domain.ReportService;
import com.securisuite.backend.report.domain.info.DashBoardInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportFacade {
    private final ReportService reportService;

    public DashBoardInfo dashBoard() {
        var dashBoardInfo = reportService.dashBoard();
        return dashBoardInfo;
    }
}
