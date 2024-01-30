package com.securisuite.backend.httrack;

import com.securisuite.backend.httrack.domain.HttrackService;
import com.securisuite.backend.httrack.domain.command.UrlCmd;
import com.securisuite.backend.httrack.domain.completion.command.CompletionCmd;
import com.securisuite.backend.httrack.domain.completion.info.CompletionInfo;
import com.securisuite.backend.httrack.domain.info.HttrackInfo;
import com.securisuite.backend.httrack.domain.wget.info.WgetInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HttrackFacade {
    private final HttrackService httrackService;

    public HttrackInfo execute(UrlCmd urlCmd) {
        var HttrackInfo = httrackService.execute(urlCmd);
        return HttrackInfo;
    }

    public CompletionInfo completion(CompletionCmd CompletionCmd){
        var completionInfo = httrackService.completion(CompletionCmd);
        return completionInfo;
    }

    public WgetInfo wget(UrlCmd urlCmd) {
        var WgetInfo = httrackService.wget(urlCmd);
        return WgetInfo;
    }
}
