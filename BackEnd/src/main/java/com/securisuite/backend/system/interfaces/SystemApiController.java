package com.securisuite.backend.system.interfaces;

import com.securisuite.backend.common.response.CommonResponse;
import com.securisuite.backend.system.SystemFacade;
import com.securisuite.backend.system.interfaces.dto.PidResponse;
import com.securisuite.backend.system.interfaces.dto.ProcessNameRequest;
import com.securisuite.backend.system.interfaces.dto.SystemDto;
import com.securisuite.backend.system.interfaces.dto.TerminateResponse;
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
@RequestMapping("/api/v1/system")
public class SystemApiController {
    private final SystemFacade systemFacade;

    @PostMapping("/execute")
    public CommonResponse execute(@RequestBody @Valid SystemDto.SystemRequest request) {
        var systemCmd = request.toCommand();
        var systemInfo = systemFacade.execute(systemCmd);
        var response = new SystemDto.SystemResponse(systemInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/pid")
    public CommonResponse pid(@RequestBody @Valid ProcessNameRequest request) {
        var pidCmd = request.toCommand();
        var pidInfo = systemFacade.getPid(pidCmd);
        var response = new PidResponse(pidInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/terminate")
    public CommonResponse terminate(@RequestBody @Valid ProcessNameRequest request) {
        var pidCmd = request.toCommand();
        var terminateInfo = systemFacade.terminate(pidCmd);
        var response = new TerminateResponse(terminateInfo);
        return CommonResponse.success(response);
    }
}