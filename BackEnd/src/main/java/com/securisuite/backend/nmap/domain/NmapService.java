package com.securisuite.backend.nmap.domain;

import com.securisuite.backend.nmap.domain.command.NmapCmd;
import com.securisuite.backend.nmap.domain.info.NmapInfo;

public interface NmapService {
    NmapInfo execute(NmapCmd nmapCmd);
}

