package com.securisuite.backend.report.domain;

import com.securisuite.backend.crunch.infrastructure.CrunchRepository;
import com.securisuite.backend.httrack.infrastructure.HttrackRepository;
import com.securisuite.backend.johntheripper.infrastructure.JohnRepository;
import com.securisuite.backend.nmap.infrastructure.NmapRepository;
import com.securisuite.backend.report.domain.info.DashBoardInfo;
import com.securisuite.backend.report.domain.info.FileListInfo;
import com.securisuite.backend.report.domain.info.LogListInfo;
import com.securisuite.backend.system.infrastructure.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final NmapRepository nmapRepository;
    private final HttrackRepository httrackRepository;
    private final CrunchRepository crunchRepository;
    private final JohnRepository johnRepository;
    private final SystemRepository systemRepository;

    @Override
    public DashBoardInfo dashBoard() {
        DashBoardInfo dashBoardInfo = DashBoardInfo.builder()
                .nmap(nmapRepository.count())
                .httrack(httrackRepository.count())
                .crunch(crunchRepository.count())
                .john(johnRepository.count())
                .system(systemRepository.count())
                .build();
        return dashBoardInfo;
    }

    @Override
    public FileListInfo fileList() {
        return null;
    }

    @Override
    public LogListInfo logList() {
        return null;
    }
}
