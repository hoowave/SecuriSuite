package com.securisuite.backend.johntheripper.domain;


import com.securisuite.backend.johntheripper.domain.command.JohnCmd;
import com.securisuite.backend.johntheripper.domain.completion.command.CompletionCmd;
import com.securisuite.backend.johntheripper.domain.completion.info.CompletionInfo;
import com.securisuite.backend.johntheripper.domain.info.JohnInfo;

public interface JohnService {
    JohnInfo execute(JohnCmd johnCmd);
    CompletionInfo completion(CompletionCmd completionCmd);
}
