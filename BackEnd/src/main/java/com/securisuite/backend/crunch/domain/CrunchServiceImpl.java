package com.securisuite.backend.crunch.domain;

import com.securisuite.backend.common.service.FileManager;
import com.securisuite.backend.common.service.ShellCommandExecutor;
import com.securisuite.backend.crunch.domain.command.CrunchCmd;
import com.securisuite.backend.crunch.domain.info.CrunchInfo;
import com.securisuite.backend.crunch.domain.progress.command.ProgressCmd;
import com.securisuite.backend.crunch.domain.progress.info.ProgressInfo;
import com.securisuite.backend.crunch.infrastructure.CrunchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrunchServiceImpl implements CrunchService {
    private final ShellCommandExecutor shellCommandExecutor;
    private final FileManager fileManager;
    private final CrunchRepository crunchRepository;

    @Override
    public CrunchInfo execute(CrunchCmd crunchCmd) {
        var initCmd = crunchCmd.toEntity();
        shellCommandExecutor.execute(initCmd.getTransCmd());
        String log = shellCommandExecutor.getLog(initCmd.getOutputLogName());
        crunchRepository.save(initCmd);
        return new CrunchInfo(initCmd, log);
    }

    @Override
    public ProgressInfo progress(ProgressCmd progressCmd) {
        var initCmd = progressCmd.toEntity();
        shellCommandExecutor.execute(initCmd.getTransCmd(), initCmd.getOutputProgressName());
        String log = shellCommandExecutor.getLog(initCmd.getOutputProgressName());
        // 로그 정보를 먼저 가져온 뒤
        var progressInfo = new ProgressInfo(initCmd, log);
        // 로그 파일 삭제
        fileManager.deleteLog(initCmd.getOutputProgressName());
        if (progressInfo.getPercent().equals("100%")) fileManager.deleteLog(initCmd.getOutputLogName());
        return progressInfo;
    }
}
