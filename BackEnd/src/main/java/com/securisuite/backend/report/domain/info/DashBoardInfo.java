package com.securisuite.backend.report.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class DashBoardInfo {
    Map<String, Long> counts = new HashMap<>();

    @Builder
    public DashBoardInfo(Long nmap, Long httrack, Long crunch, Long john, Long system) {
        counts.put("nmap", nmap);
        counts.put("httrack", httrack);
        counts.put("crunch", crunch);
        counts.put("john", john);
        counts.put("system", system);
    }
}
