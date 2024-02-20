package com.securisuite.backend.johntheripper.domain;

import com.securisuite.backend.common.exception.BaseException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
@Entity
public class John {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "파일은 필수값입니다.")
    @Transient
    private MultipartFile targetFile;
    @NotNull(message = "옵션은 필수값입니다.")
    @Enumerated(EnumType.STRING)
    private JohnOption johnOption;
    @Transient
    private MultipartFile customList;
    @Enumerated(EnumType.STRING)
    private JohnType johnType;
    @Transient
    private String[] transCmd;
    private String regDts;
    private String logName;
    private String hashName;
    private String targetFileName;
    private String customListName;
    private char complete;

    @Builder
    public John(MultipartFile targetFile, JohnOption johnOption, MultipartFile customList) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.regDts = format.format(new Date());
        this.logName = this.regDts + "_johnTheRipper.txt";
        this.hashName = this.regDts + "_hash.txt";
        this.complete = 'N';

        this.targetFile = targetFile;
        this.johnOption = johnOption;
        this.johnType = johnTypeSetting(targetFile);
        this.targetFileName = this.regDts + "_" + targetFile.getOriginalFilename();
        transCmd = new String[3];
        transCmd[0] = "/bin/sh";
        transCmd[1] = "-c";

        if (johnOption == JohnOption.DEFAULT) {
            transCmd[2] = johnType.getType() + " /var/www/html/upload/" + targetFileName + " > /var/www/html/download/logs/" + hashName + " && " +
                    "john /var/www/html/download/logs/" + hashName + " && " +
                    "john /var/www/html/download/logs/" + hashName + " --show > /var/www/html/download/logs/" + logName;
        } else {
            // 사용자 지정 선택 후 사용자 지정 파일을 업로드 하지 않았을 경우 Exception
            if ((customList == null || customList.isEmpty())) throw new BaseException("사용자 리스트는 필수입니다.");
            this.customListName = this.regDts + "_" + customList.getOriginalFilename();
            this.customList = customList;
                transCmd[2] = johnType.getType() + " /var/www/html/upload/" + targetFileName + " > /var/www/html/download/logs/" + hashName + " && " +
                        "john -w:/var/www/html/upload/" + customListName + " /var/www/html/download/logs/" + hashName + " && " +
                        "john /var/www/html/download/logs/" + hashName + " --show > /var/www/html/download/logs/" + logName;

        }
    }

    // 파일 확장자에 맞게 johnType 세팅
    private JohnType johnTypeSetting(MultipartFile targetFile) {
        Set<String> ARCHIVE_EXTENSIONS = new HashSet<>(Arrays.asList(".zip", ".rar", ".7z"));
        Set<String> OFFICE_EXTENSIONS = new HashSet<>(Arrays.asList(".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx"));
        String fileName = targetFile.getOriginalFilename();
        String fileExtension = "";

        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        }

        if (ARCHIVE_EXTENSIONS.contains(fileExtension)) {
            return JohnType.ARCHIVE;
        } else if (OFFICE_EXTENSIONS.contains(fileExtension)) {
            return JohnType.OFFICE;
        }
        throw new BaseException("지원하지 않는 확장자입니다.");
    }

    public void Complete() {
        this.complete = 'Y';
    }

}
