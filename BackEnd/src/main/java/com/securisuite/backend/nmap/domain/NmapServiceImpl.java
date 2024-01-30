package com.securisuite.backend.nmap.domain;

import com.securisuite.backend.common.service.ShellCommandExecutor;
import com.securisuite.backend.nmap.domain.command.NmapCmd;
import com.securisuite.backend.nmap.domain.info.NmapInfo;
import com.securisuite.backend.nmap.infrastructure.NmapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NmapServiceImpl implements NmapService {
    private final ShellCommandExecutor shellCommandExecutor;
    private final NmapRepository nmapRepository;

    @Override
    public NmapInfo execute(NmapCmd nmapCmd) {
        var initCmd = nmapCmd.toEntity();
        shellCommandExecutor.execute(initCmd.getTransCmd(), initCmd.getLogName());
        String log = shellCommandExecutor.getLog(initCmd.getLogName());
        nmapRepository.save(initCmd);
        return new NmapInfo(initCmd, log);
    }
}