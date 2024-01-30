package com.securisuite.backend.system.domain;


import com.securisuite.backend.system.domain.command.SystemCmd;
import com.securisuite.backend.system.domain.info.SystemInfo;
import com.securisuite.backend.system.domain.process.ProcessNameCmd;
import com.securisuite.backend.system.domain.process.pid.info.PidInfo;
import com.securisuite.backend.system.domain.process.terminate.info.TerminateInfo;

public interface SystemService {

    SystemInfo execute(SystemCmd systemCmd);

    // 프로세스 이름을 통해 PID 전달받음
    PidInfo getPid(ProcessNameCmd processNameCmd);

    // PID를 통해 작업 강제종료
    TerminateInfo terminate(PidInfo pidInfo);
}
