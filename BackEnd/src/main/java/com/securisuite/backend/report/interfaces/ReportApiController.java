package com.securisuite.backend.report.interfaces;

import com.securisuite.backend.common.response.CommonResponse;
import com.securisuite.backend.report.ReportFacade;
import com.securisuite.backend.report.interfaces.dto.DashBoardResponse;
import com.securisuite.backend.report.interfaces.dto.FileListResponse;
import com.securisuite.backend.report.interfaces.dto.LogListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportApiController {
    private final ReportFacade reportFacade;

    @PostMapping("/dashboard")
    public CommonResponse dashBoard() {
        var dashBoardInfo = reportFacade.dashBoard();
        var response = new DashBoardResponse(dashBoardInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/files")
    public CommonResponse fileList() {
        var fileListInfo = reportFacade.fileList();
        var response = new FileListResponse(fileListInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/logs")
    public CommonResponse logList() {
        var logListInfo = reportFacade.logList();
        var response = new LogListResponse(logListInfo);
        return CommonResponse.success(response);
    }
}
