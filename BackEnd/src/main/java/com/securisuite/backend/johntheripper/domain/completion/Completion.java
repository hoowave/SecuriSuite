package com.securisuite.backend.johntheripper.domain.completion;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class Completion {
    @NotEmpty(message = "일시는 필수값입니다.")
    private String regDts;
    private String logName;
    private String hashName;

    @Builder
    public Completion(String regDts) {
        this.regDts = regDts;
        this.logName = this.regDts + "_johnTheRipper.txt";
        this.hashName = this.regDts + "_hash.txt";
    }
}
