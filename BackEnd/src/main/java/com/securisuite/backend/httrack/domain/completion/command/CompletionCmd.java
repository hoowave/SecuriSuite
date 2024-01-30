package com.securisuite.backend.httrack.domain.completion.command;

import com.securisuite.backend.httrack.domain.completion.Completion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CompletionCmd {
    private String url;
    private String regDts;

    public Completion toEntity() {
        return Completion.builder()
                .url(url)
                .regDts(regDts)
                .build();
    }
}
