package com.securisuite.backend.nmap.domain;

import com.securisuite.backend.common.exception.BaseException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Nmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NmapOption option;
    @NotEmpty(message = "IP는 필수값입니다.")
    private String ip;
    private String port;
    @Transient
    private String[] transCmd;
    private String regDts;
    private String logName;
    private char complete;

    @Builder
    public Nmap(NmapOption option, String ip, String port) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_nmap.txt";
        this.complete = 'N';

        // 포트 지정 스캔 후 포트를 지정하지 않았을 경우 Exception
        if (option == NmapOption.SET && (port == null || port.isEmpty())) throw new BaseException("포트 지정은 필수입니다.");
        // 쉘 명령 조합
        if (option == NmapOption.SET) {
            this.option = option;
            this.ip = ip;
            this.port = port;
            transCmd = new String[4];
            transCmd[0] = "nmap";
            transCmd[1] = option.getOption();
            transCmd[2] = port;
            transCmd[3] = ip;
        }
        if (option == NmapOption.ALL) {
            this.option = option;
            this.ip = ip;
            transCmd = new String[3];
            transCmd[0] = "nmap";
            transCmd[1] = option.getOption();
            transCmd[2] = ip;
        }
    }

    public void Complete() {
        this.complete = 'Y';
    }
}
