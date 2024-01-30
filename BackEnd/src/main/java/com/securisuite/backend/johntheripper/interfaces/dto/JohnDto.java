package com.securisuite.backend.johntheripper.interfaces.dto;

import com.securisuite.backend.johntheripper.domain.JohnOption;
import com.securisuite.backend.johntheripper.domain.JohnType;
import com.securisuite.backend.johntheripper.domain.command.JohnCmd;
import com.securisuite.backend.johntheripper.domain.info.JohnInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

public class JohnDto {
    @Getter
    @Setter
    @ToString
    public static class JohnRequest {
        @NotNull(message = "파일은 필수값입니다.")
        private MultipartFile targetFile;
        @NotNull(message = "옵션은 필수값입니다.")
        private JohnOption johnOption;
        private MultipartFile customList;

        public JohnCmd toCommand() {
            return JohnCmd.builder()
                    .targetFile(targetFile)
                    .johnOption(johnOption)
                    .customList(customList)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class JohnResponse {
        private JohnOption johnOption;
        private JohnType johnType;
        private String cmd;
        private String regDts;
        private String logName;
        private String hashName;
        private String targetFileName;
        private String customListName;

        public JohnResponse(JohnInfo johnInfo) {
            this.johnOption = johnInfo.getJohnOption();
            this.johnType = johnInfo.getJohnType();
            this.cmd = johnInfo.getCmd();
            this.regDts = johnInfo.getRegDts();
            this.logName = johnInfo.getLogName();
            this.hashName = johnInfo.getHashName();
            this.targetFileName = johnInfo.getTargetFileName();
            this.customListName = johnInfo.getCustomListName();
        }
    }
}
