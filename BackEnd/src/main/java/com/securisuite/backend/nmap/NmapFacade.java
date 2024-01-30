package com.securisuite.backend.nmap;

import com.securisuite.backend.nmap.domain.NmapService;
import com.securisuite.backend.nmap.domain.command.NmapCmd;
import com.securisuite.backend.nmap.domain.info.NmapInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NmapFacade {

    private final NmapService nmapService;

    public NmapInfo execute(NmapCmd nmapCmd) {
        var nmapInfo = nmapService.execute(nmapCmd);
        return nmapInfo;
    }
}
