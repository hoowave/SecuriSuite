package com.securisuite.backend.crunch.domain.info;

import com.securisuite.backend.crunch.domain.Crunch;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class CrunchInfo {
    private int minWord;
    private int maxWord;
    private String words;
    private String cmd;
    private String regDts;
    private String logName;
    private String log;
    private CrunchResult crunchResult;

    public CrunchInfo(Crunch crunch, String log) {
        this.minWord = crunch.getMinWord();
        this.maxWord = crunch.getMaxWord();
        this.words = crunch.getWords();
        this.cmd = "";
        for (String c : crunch.getTransCmd()) this.cmd += c + " ";
        this.regDts = crunch.getRegDts();
        this.logName = crunch.getLogName();
        this.log = log;
        this.crunchResult = getInfoResult();
    }

    private CrunchResult getInfoResult() {
        CrunchResult.CrunchResultBuilder rsBuilder = CrunchResult.builder();
        Pattern pattern = Pattern.compile("(\\d+)\\s+(bytes|MB|GB|TB|PB)");
        Matcher matcher = pattern.matcher(log);
        while (matcher.find()) {
            String value = matcher.group(1); // 숫자 값
            String unit = matcher.group(2); // 단위 (bytes, MB, GB, TB, PB)
            switch (unit) {
                case "bytes":
                    rsBuilder.amountByte(value);
                    break;
                case "MB":
                    rsBuilder.amountMb(value);
                    break;
                case "GB":
                    rsBuilder.amountGb(value);
                    break;
                case "TB":
                    rsBuilder.amountTb(value);
                    break;
                case "PB":
                    rsBuilder.amountPb(value);
                    break;
            }
        }

        // 줄(line) 수 파싱
        Pattern linePattern = Pattern.compile("number of lines: (\\d+)");
        Matcher lineMatcher = linePattern.matcher(log);
        if (lineMatcher.find()) {
            String lines = lineMatcher.group(1);
            rsBuilder.amountLine(lines);
        }
        return rsBuilder.build();
    }
}
