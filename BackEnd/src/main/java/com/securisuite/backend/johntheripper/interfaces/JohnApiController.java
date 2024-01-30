package com.securisuite.backend.johntheripper.interfaces;


import com.securisuite.backend.common.response.CommonResponse;
import com.securisuite.backend.johntheripper.JohnFacade;
import com.securisuite.backend.johntheripper.interfaces.dto.CompletionDto;
import com.securisuite.backend.johntheripper.interfaces.dto.JohnDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/john")
public class JohnApiController {
    private final JohnFacade johnFacade;

    @PostMapping("/execute")
    public CommonResponse execute(@ModelAttribute @Valid JohnDto.JohnRequest request) {
        var johnCmd = request.toCommand();
        var johnInfo = johnFacade.execute(johnCmd);
        var response = new JohnDto.JohnResponse(johnInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/completion")
    public CommonResponse completion(@RequestBody @Valid CompletionDto.CompletionRequest request) {
        var completionCmd = request.toCommand();
        var completionInfo = johnFacade.completion(completionCmd);
        var response = new CompletionDto.CompletionResponse(completionInfo);
        return CommonResponse.success(response);
    }
}
