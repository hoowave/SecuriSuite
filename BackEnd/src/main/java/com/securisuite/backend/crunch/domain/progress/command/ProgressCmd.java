package com.securisuite.backend.crunch.domain.progress.command;

import com.securisuite.backend.crunch.domain.progress.Progress;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProgressCmd {
    private String regDts;

    public Progress toEntity() {
        return Progress.builder()
                .regDts(regDts)
                .build();
    }
}
