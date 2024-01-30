package com.securisuite.backend.crunch;


import com.securisuite.backend.crunch.domain.CrunchService;
import com.securisuite.backend.crunch.domain.command.CrunchCmd;
import com.securisuite.backend.crunch.domain.info.CrunchInfo;
import com.securisuite.backend.crunch.domain.progress.command.ProgressCmd;
import com.securisuite.backend.crunch.domain.progress.info.ProgressInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrunchFacade {
    private final CrunchService crunchService;

    public CrunchInfo execute(CrunchCmd crunchCmd) {
        var crunchInfo = crunchService.execute(crunchCmd);
        return crunchInfo;
    }

    public ProgressInfo progress(ProgressCmd progressCmd) {
        var progressInfo = crunchService.progress(progressCmd);
        return progressInfo;
    }
}
