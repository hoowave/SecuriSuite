package com.securisuite.backend.crunch.domain.info;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CrunchResult {
    private String amountByte;
    private String amountMb;
    private String amountGb;
    private String amountTb;
    private String amountPb;
    private String amountLine;
}
