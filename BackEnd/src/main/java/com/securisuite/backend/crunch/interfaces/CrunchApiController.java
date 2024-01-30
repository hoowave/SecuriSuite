package com.securisuite.backend.crunch.interfaces;

import com.securisuite.backend.common.response.CommonResponse;
import com.securisuite.backend.crunch.CrunchFacade;
import com.securisuite.backend.crunch.interfaces.dto.CrunchDto;
import com.securisuite.backend.crunch.interfaces.dto.ProgressDto;
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
@RequestMapping("/api/v1/crunch")
public class CrunchApiController {
    private final CrunchFacade crunchFacade;

    @PostMapping("/execute")
    public CommonResponse execute(@RequestBody @Valid CrunchDto.CrunchRequest request) {
        var crunchCmd = request.toCommand();
        var crunchInfo = crunchFacade.execute(crunchCmd);
        var response = new CrunchDto.CrunchResponse(crunchInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/progress")
    public CommonResponse progress(@RequestBody @Valid ProgressDto.ProgressRequest request) {
        var progressCmd = request.toCommand();
        var progressInfo = crunchFacade.progress(progressCmd);
        var response = new ProgressDto.ProgressResponse(progressInfo);
        return CommonResponse.success(response);
    }
}
