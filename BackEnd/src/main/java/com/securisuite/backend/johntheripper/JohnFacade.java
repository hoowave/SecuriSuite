package com.securisuite.backend.johntheripper;


import com.securisuite.backend.johntheripper.domain.JohnService;
import com.securisuite.backend.johntheripper.domain.command.JohnCmd;
import com.securisuite.backend.johntheripper.domain.completion.command.CompletionCmd;
import com.securisuite.backend.johntheripper.domain.completion.info.CompletionInfo;
import com.securisuite.backend.johntheripper.domain.info.JohnInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JohnFacade {
    private final JohnService johnService;

    public JohnInfo execute(JohnCmd johnCmd) {
        var johnInfo = johnService.execute(johnCmd);
        return johnInfo;
    }

    public CompletionInfo completion(CompletionCmd completionCmd) {
        var completionInfo = johnService.completion(completionCmd);
        return completionInfo;
    }
}
