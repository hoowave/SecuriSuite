package com.securisuite.backend.crunch.domain.command;

import com.securisuite.backend.crunch.domain.Crunch;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CrunchCmd {
    private int minWord;
    private int maxWord;
    private String words;

    public Crunch toEntity() {
        return Crunch.builder()
                .minWord(minWord)
                .maxWord(maxWord)
                .words(words)
                .build();
    }
}
