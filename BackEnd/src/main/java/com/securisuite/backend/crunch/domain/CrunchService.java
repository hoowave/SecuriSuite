package com.securisuite.backend.crunch.domain;


import com.securisuite.backend.crunch.domain.command.CrunchCmd;
import com.securisuite.backend.crunch.domain.info.CrunchInfo;
import com.securisuite.backend.crunch.domain.progress.command.ProgressCmd;
import com.securisuite.backend.crunch.domain.progress.info.ProgressInfo;

public interface CrunchService {
    CrunchInfo execute(CrunchCmd crunchCmd);

    ProgressInfo progress(ProgressCmd progressCmd);
}
