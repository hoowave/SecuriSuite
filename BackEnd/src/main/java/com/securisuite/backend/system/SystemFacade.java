package com.securisuite.backend.system;
import com.securisuite.backend.system.domain.SystemService;
import com.securisuite.backend.system.domain.command.SystemCmd;
import com.securisuite.backend.system.domain.info.SystemInfo;
import com.securisuite.backend.system.domain.process.ProcessNameCmd;
import com.securisuite.backend.system.domain.process.pid.info.PidInfo;
import com.securisuite.backend.system.domain.process.terminate.info.TerminateInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemFacade {
    private final SystemService systemService;

    public SystemInfo execute(SystemCmd systemCmd) {
        var systemInfo = systemService.execute(systemCmd);
        return systemInfo;
    }

    public PidInfo getPid(ProcessNameCmd processNameCmd) {
        var pidInfo = systemService.getPid(processNameCmd);
        return pidInfo;
    }

    public TerminateInfo terminate(ProcessNameCmd processNameCmd) {
        var pidInfo = systemService.getPid(processNameCmd);
        var terminateInfo = systemService.terminate(pidInfo);
        return terminateInfo;
    }
}
