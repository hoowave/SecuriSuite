package com.securisuite.backend.httrack.interfaces;

import com.securisuite.backend.common.response.CommonResponse;
import com.securisuite.backend.httrack.HttrackFacade;
import com.securisuite.backend.httrack.interfaces.dto.CompletionDto;
import com.securisuite.backend.httrack.interfaces.dto.HttrackResponse;
import com.securisuite.backend.httrack.interfaces.dto.UrlRequest;
import com.securisuite.backend.httrack.interfaces.dto.WgetResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/httrack")
public class HttrackApiController {
    private final HttrackFacade httrackFacade;

    @PostMapping("/execute")
    public CommonResponse execute(@RequestBody @Valid UrlRequest request) {
        var urlCmd = request.toCommand();
        var httrackInfo = httrackFacade.execute(urlCmd);
        var response = new HttrackResponse(httrackInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/completion")
    public CommonResponse completion(@RequestBody @Valid CompletionDto.CompletionRequest request) {
        var completionCmd = request.toCommand();
        var completionInfo = httrackFacade.completion(completionCmd);
        var response = new CompletionDto.CompletionResponse(completionInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/wget")
    public CommonResponse wget(@RequestBody @Valid UrlRequest request) {
        var urlCmd = request.toCommand();
        var wgetInfo = httrackFacade.wget(urlCmd);
        var response = new WgetResponse(wgetInfo);
        return CommonResponse.success(response);
    }

}
