package com.securisuite.backend.johntheripper.domain.info;

import com.securisuite.backend.johntheripper.domain.John;
import com.securisuite.backend.johntheripper.domain.JohnOption;
import com.securisuite.backend.johntheripper.domain.JohnType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JohnInfo {
    private JohnOption johnOption;
    private JohnType johnType;
    private String cmd;
    private String regDts;
    private String logName;
    private String hashName;
    private String targetFileName;
    private String customListName;

    public JohnInfo(John john) {
        this.johnOption = john.getJohnOption();
        this.johnType = john.getJohnType();
        this.cmd = "";
        for (String c : john.getTransCmd()) this.cmd += c + " ";
        this.regDts = john.getRegDts();
        this.logName = john.getLogName();
        this.hashName = john.getHashName();
        this.targetFileName = john.getTargetFileName();
        this.customListName = john.getCustomListName();
    }
}
