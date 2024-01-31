package com.securisuite.frontend.web;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class DashBoardResponse {
    private String result;
    private Data data;
    private String message;

    @Getter
    @ToString
    public static class Data {
        private Map<String, Integer> counts;
    }
}