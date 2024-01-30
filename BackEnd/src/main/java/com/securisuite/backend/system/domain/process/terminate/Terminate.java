package com.securisuite.backend.system.domain.process.terminate;

import com.securisuite.backend.system.domain.process.pid.info.PidInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class Terminate {
    @NotEmpty(message = "프로세스명은 필수값입니다.")
    private String processName;
    private String pid;
    private String[] transCmd;
    private String regDts;

    @Builder
    public Terminate(PidInfo pidInfo) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.processName = pidInfo.getProcessName();
        this.pid = pidInfo.getPid();
        transCmd = new String[3];
        transCmd[0] = "kill";
        transCmd[1] = "-9";
        transCmd[2] = pid;
    }
}
