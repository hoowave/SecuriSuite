package com.securisuite.frontend.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebFacade {
    private final RestTemplate restTemplate;

    public Map<String, Integer> getDashBoard() {
        String url = "http://172.16.234.132:16102/api/v1/report/dashboard";
        DashBoardResponse dashBoardResponse = restTemplate.postForObject(url, null, DashBoardResponse.class);
        var response = dashBoardResponse.getData().getCounts();
        return response;
    }
}
