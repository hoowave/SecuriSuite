package com.securisuite.backend.crunch.domain.progress.info;

import com.securisuite.backend.crunch.domain.progress.Progress;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class ProgressInfo {
    private String regDts;
    private String log;
    private String logName;
    private String percent;

    public ProgressInfo(Progress progress, String log) {
        this.regDts = progress.getRegDts();
        this.log = log;
        this.logName = this.regDts + "_crunch.txt";
        this.percent = getInfoResult();
    }

    private String getInfoResult() {
        Pattern pattern = Pattern.compile("crunch:\\s+(\\d+%)");
        Matcher matcher = pattern.matcher(log);

        if (matcher.find()) {
            return matcher.group(1); // 숫자와 % 기호를 함께 반환
        }
        return "0%"; // 패턴이 매치되지 않으면 0% 반환
    }
}
