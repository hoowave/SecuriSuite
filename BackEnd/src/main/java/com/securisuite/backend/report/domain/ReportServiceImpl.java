package com.securisuite.backend.report.domain;

import com.securisuite.backend.crunch.domain.Crunch;
import com.securisuite.backend.crunch.infrastructure.CrunchRepository;
import com.securisuite.backend.httrack.domain.Httrack;
import com.securisuite.backend.httrack.domain.wget.Wget;
import com.securisuite.backend.httrack.infrastructure.HttrackRepository;
import com.securisuite.backend.httrack.infrastructure.WgetRepository;
import com.securisuite.backend.johntheripper.infrastructure.JohnRepository;
import com.securisuite.backend.nmap.infrastructure.NmapRepository;
import com.securisuite.backend.report.domain.info.*;
import com.securisuite.backend.system.infrastructure.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final NmapRepository nmapRepository;
    private final HttrackRepository httrackRepository;
    private final WgetRepository wgetRepository;
    private final CrunchRepository crunchRepository;
    private final JohnRepository johnRepository;
    private final SystemRepository systemRepository;

    @Override
    public DashBoardInfo dashBoard() {
        DashBoardInfo dashBoardInfo = DashBoardInfo.builder()
                .nmap(nmapRepository.count())
                .httrack(httrackRepository.count() + wgetRepository.count())
                .crunch(crunchRepository.count())
                .john(johnRepository.count())
                .system(systemRepository.count())
                .build();
        return dashBoardInfo;
    }

    @Override
    public FileListInfo fileList() {
        List<Httrack> httrackList = httrackRepository.findAll();
        List<Crunch> crunchList = crunchRepository.findAll();
        List<Wget> wgetList = wgetRepository.findAll();

        List<HttrackFileInfo> httrackFileInfoList = new ArrayList<>();

        for (Httrack httrack : httrackList) {
            HttrackFileInfo fileInfo = HttrackFileInfo.builder()
                    .httrackRegDts(httrack.getRegDts())
                    .httrackTransUrl(httrack.getTransUrl())
                    .httrackUrl(httrack.getUrl())
                    .httrackType("httrack")
                    .build();
            httrackFileInfoList.add(fileInfo);
        }

        for (Wget wget : wgetList) {
            HttrackFileInfo fileInfo = HttrackFileInfo.builder()
                    .httrackRegDts(wget.getRegDts())
                    .httrackTransUrl(wget.getTransUrl())
                    .httrackUrl(wget.getUrl())
                    .httrackType("wget")
                    .build();
            httrackFileInfoList.add(fileInfo);
        }

        Collections.sort(httrackFileInfoList);

        Map<Integer, CrunchFileInfo> crunchMap = new HashMap<>();
        int crunchCnt = 1;
        for (Crunch crunch : crunchList) {
            CrunchFileInfo fileInfo = CrunchFileInfo.builder()
                    .crunchMinWord(crunch.getMinWord())
                    .crunchMaxWord(crunch.getMaxWord())
                    .crunchWords(crunch.getWords())
                    .crunchRegDts(crunch.getRegDts())
                    .crunchLogName(crunch.getLogName())
                    .build();
            crunchMap.put(crunchCnt++, fileInfo);
        }
        return new FileListInfo(httrackFileInfoList, crunchMap);
    }

    @Override
    public List<LogListInfo> logList() {
        List<LogListInfo> logs = new ArrayList<>();
        nmapRepository.findAll().forEach(nmap ->
                logs.add(new LogListInfo("nmap", nmap.getLogName(), nmap.getRegDts()))
        );
        johnRepository.findAll().forEach(john ->
                logs.add(new LogListInfo("john", john.getLogName(), john.getRegDts()))
        );
        systemRepository.findAll().forEach(system ->
                logs.add(new LogListInfo("system", system.getLogName(), system.getRegDts()))
        );
        Collections.sort(logs);
        return logs;
    }
}
