package com.securisuite.backend.johntheripper.domain.command;

import com.securisuite.backend.johntheripper.domain.John;
import com.securisuite.backend.johntheripper.domain.JohnOption;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class JohnCmd {
    private MultipartFile targetFile;
    private JohnOption johnOption;
    private MultipartFile customList;

    public John toEntity() {
        return John.builder()
                .targetFile(targetFile)
                .johnOption(johnOption)
                .customList(customList)
                .build();
    }
}
