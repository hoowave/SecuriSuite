package com.securisuite.backend.johntheripper.domain;

import com.securisuite.backend.common.service.FileManager;
import com.securisuite.backend.common.service.ShellCommandExecutor;
import com.securisuite.backend.johntheripper.domain.command.JohnCmd;
import com.securisuite.backend.johntheripper.domain.completion.command.CompletionCmd;
import com.securisuite.backend.johntheripper.domain.completion.info.CompletionInfo;
import com.securisuite.backend.johntheripper.domain.info.JohnInfo;
import com.securisuite.backend.johntheripper.infrastructure.JohnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JohnServiceImpl implements JohnService {
    private final ShellCommandExecutor shellCommandExecutor;
    private final FileManager fileManager;
    private final JohnRepository johnRepository;

    public JohnInfo execute(JohnCmd johnCmd) {
        var initCmd = johnCmd.toEntity();
        fileManager.uploadFile(initCmd.getTargetFile(), initCmd.getTargetFileName());
        if (initCmd.getJohnOption() == JohnOption.CUSTOM) {
            fileManager.uploadFile(initCmd.getCustomList(), initCmd.getCustomListName());
        }
        shellCommandExecutor.execute(initCmd.getTransCmd());
        johnRepository.save(initCmd);
        return new JohnInfo(initCmd);
    }

    @Override
    public CompletionInfo completion(CompletionCmd completionCmd) {
        var initCmd = completionCmd.toEntity();
        boolean isHashFile = fileManager.isLogExist(initCmd.getHashName());
        boolean isLogFile = fileManager.isLogExist(initCmd.getLogName());
        boolean complete;
        String message;
        String log = "";

        if (isHashFile) {
            if (isLogFile) {
                complete = true;
                message = "작업이 완료되었습니다.";
                log = shellCommandExecutor.getLog(initCmd.getLogName());
            } else {
                complete = false;
                message = "작업이 진행중입니다.";
            }
        } else {
            complete = false;
            message = "유효한 작업이 아닙니다.";
        }
        return new CompletionInfo(initCmd, complete, message, log);
    }
}
