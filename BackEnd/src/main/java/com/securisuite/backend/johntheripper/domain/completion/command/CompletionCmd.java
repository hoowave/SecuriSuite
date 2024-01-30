package com.securisuite.backend.johntheripper.domain.completion.command;

import com.securisuite.backend.johntheripper.domain.completion.Completion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CompletionCmd {
    private String regDts;

    public Completion toEntity() {
        return Completion.builder()
                .regDts(regDts)
                .build();
    }
}
