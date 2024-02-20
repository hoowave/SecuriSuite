package com.securisuite.frontend.config;

import org.springframework.stereotype.Component;

@Component
public class PathConfig {
    public static final String VIEWS_PATH = "views/";
    public static final String TOOLS_PATH = "views/tools/";
    public static final String BASE_IP = "host.docker.internal";
    //public static final String BASE_IP = "localhost";
    public static final String API_BASE_URL = "http://" + BASE_IP + ":16102/api/v1/";
}
