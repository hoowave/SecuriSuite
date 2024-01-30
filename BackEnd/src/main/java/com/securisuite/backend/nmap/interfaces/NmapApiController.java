package com.securisuite.backend.nmap.interfaces;

import com.securisuite.backend.common.response.CommonResponse;
import com.securisuite.backend.nmap.NmapFacade;
import com.securisuite.backend.nmap.interfaces.dto.NmapDto;
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
@RequestMapping("/api/v1/nmap")
public class NmapApiController {

    private final NmapFacade nmapFacade;

    @PostMapping("/execute")
    public CommonResponse execute(@RequestBody @Valid NmapDto.NmapRequest request) {
        var nMapCmd = request.toCommand();
        var nmapInfo = nmapFacade.execute(nMapCmd);
        var response = new NmapDto.NmapResponse(nmapInfo);
        return CommonResponse.success(response);
    }
}