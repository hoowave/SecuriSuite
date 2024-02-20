package com.securisuite.frontend.web;

import com.securisuite.frontend.web.dto.DashBoardResponse;
import com.securisuite.frontend.web.dto.FileListResponse;
import com.securisuite.frontend.web.dto.LogListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.securisuite.frontend.config.PathConfig.API_BASE_URL;


@Slf4j
@Service
@RequiredArgsConstructor
public class WebFacade {
    private final RestTemplate restTemplate;

    public Object getDashBoard() {
        String url = API_BASE_URL + "report/dashboard";
        DashBoardResponse dashBoardResponse = restTemplate.postForObject(url, null, DashBoardResponse.class);
        var response = dashBoardResponse.getData().getCounts();
        return response;
    }

    public Object getFileList() {
        String url = API_BASE_URL + "report/files";
        FileListResponse fileListResponse = restTemplate.postForObject(url, null, FileListResponse.class);
        var response = fileListResponse.getData();
        return response;
    }

    public Object getLogList() {
        String url = API_BASE_URL + "report/logs";
        LogListResponse logListResponse = restTemplate.postForObject(url, null, LogListResponse.class);
        var response = logListResponse.getData();
        return response;
    }
}
