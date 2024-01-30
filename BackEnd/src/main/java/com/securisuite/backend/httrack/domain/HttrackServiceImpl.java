package com.securisuite.backend.httrack.domain;

import com.securisuite.backend.common.service.FileManager;
import com.securisuite.backend.common.service.ShellCommandExecutor;
import com.securisuite.backend.httrack.domain.command.UrlCmd;
import com.securisuite.backend.httrack.domain.completion.command.CompletionCmd;
import com.securisuite.backend.httrack.domain.completion.info.CompletionInfo;
import com.securisuite.backend.httrack.domain.info.HttrackInfo;
import com.securisuite.backend.httrack.domain.wget.info.WgetInfo;
import com.securisuite.backend.httrack.infrastructure.HttrackRepository;
import com.securisuite.backend.httrack.infrastructure.WgetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HttrackServiceImpl implements HttrackService {
    private final ShellCommandExecutor shellCommandExecutor;
    private final FileManager fileManager;
    private final HttrackRepository httrackRepository;
    private final WgetRepository wgetRepository;

    @Override
    public HttrackInfo execute(UrlCmd urlCmd) {
        var initCmd = urlCmd.toHttrackEntity();
        shellCommandExecutor.execute(initCmd.getTransCmd(), initCmd.getLogName());
        httrackRepository.save(initCmd);
        return new HttrackInfo(initCmd);
    }

    @Override
    public CompletionInfo completion(CompletionCmd completionCmd) {
        var initCmd = completionCmd.toEntity();
        boolean isLogFile = fileManager.isLogExist(initCmd.getLogName());
        boolean isZipFile = fileManager.isFileExist(initCmd.getFileName());
        boolean complete;
        String message;

        if (isLogFile) {
            if (isZipFile) {
                complete = true;
                message = "작업이 완료되었습니다.";
                fileManager.deleteLog(initCmd.getTempLog());
            } else {
                complete = false;
                message = "작업이 진행중입니다.";
            }
        } else {
            complete = false;
            message = "유효한 작업이 아닙니다.";
        }
        return new CompletionInfo(initCmd, complete, message);
    }

    @Override
    public WgetInfo wget(UrlCmd urlCmd) {
        var initCmd = urlCmd.toWgetEntity();
        shellCommandExecutor.execute(initCmd.getTransCmd());
        wgetRepository.save(initCmd);
        return new WgetInfo(initCmd);
    }
}
