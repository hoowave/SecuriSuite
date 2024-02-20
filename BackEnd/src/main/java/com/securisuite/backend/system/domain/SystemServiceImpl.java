package com.securisuite.backend.system.domain;

import com.securisuite.backend.common.exception.BaseException;
import com.securisuite.backend.common.service.FileManager;
import com.securisuite.backend.common.service.ShellCommandExecutor;
import com.securisuite.backend.system.domain.command.SystemCmd;
import com.securisuite.backend.system.domain.info.SystemInfo;
import com.securisuite.backend.system.domain.process.ProcessNameCmd;
import com.securisuite.backend.system.domain.process.pid.info.PidInfo;
import com.securisuite.backend.system.domain.process.terminate.Terminate;
import com.securisuite.backend.system.domain.process.terminate.info.TerminateInfo;
import com.securisuite.backend.system.infrastructure.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final ShellCommandExecutor shellCommandExecutor;
    private final FileManager fileManager;
    private final SystemRepository systemRepository;

    @Override
    public SystemInfo execute(SystemCmd systemCmd) {
        var initCmd = systemCmd.toEntity();
        systemRepository.save(initCmd);
        shellCommandExecutor.execute(initCmd.getTransCmd(), initCmd.getLogName());
        String log = shellCommandExecutor.getLog(initCmd.getLogName());
        initCmd.Complete();
        systemRepository.save(initCmd);
        return new SystemInfo(initCmd, log);
    }

    public PidInfo getPid(ProcessNameCmd processNameCmd) {
        var initCmd = processNameCmd.toEntity();
        shellCommandExecutor.execute(initCmd.getTransCmd(), initCmd.getLogName());
        String log = shellCommandExecutor.getLog(initCmd.getLogName());
        // PID 정보를 먼저 가져온 뒤
        var pidInfo = new PidInfo(initCmd, log);
        // 로그 삭제
        fileManager.deleteLog(initCmd.getLogName());
        if (pidInfo.getPid() == null) { // Optional 변경
            throw new BaseException("해당 프로세스(작업)는 존재하지 않습니다.");
        }
        return pidInfo;
    }

    public TerminateInfo terminate(PidInfo pidInfo) {
        var initCmd = new Terminate(pidInfo);
        shellCommandExecutor.execute(initCmd.getTransCmd());
        return new TerminateInfo(initCmd);
    }
}
