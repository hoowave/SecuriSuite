package com.securisuite.backend.httrack.domain;


import com.securisuite.backend.httrack.domain.command.UrlCmd;
import com.securisuite.backend.httrack.domain.completion.command.CompletionCmd;
import com.securisuite.backend.httrack.domain.completion.info.CompletionInfo;
import com.securisuite.backend.httrack.domain.info.HttrackInfo;
import com.securisuite.backend.httrack.domain.wget.info.WgetInfo;

public interface HttrackService {
    HttrackInfo execute(UrlCmd urlCmd);

    CompletionInfo completion(CompletionCmd completionCmd);

    WgetInfo wget(UrlCmd urlCmd);
}
