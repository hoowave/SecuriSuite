package com.securisuite.backend.johntheripper.domain.completion.info;

import com.securisuite.backend.johntheripper.domain.completion.Completion;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class CompletionInfo {
    private String regDts;
    private String logName;
    private String hashName;
    private boolean complete;
    private String message;
    private String password;

    public CompletionInfo(Completion completion, boolean complete, String message, String log) {
        this.regDts = completion.getRegDts();
        this.logName = completion.getLogName();
        this.hashName = completion.getHashName();
        this.complete = complete;
        this.message = message;
        this.password = getPassword(log);
    }

    private String getPassword(String log) {
        String regex = ":(.*?):";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
