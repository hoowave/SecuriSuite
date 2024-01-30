package com.securisuite.backend.common.config;

import org.springframework.stereotype.Component;

@Component
public class PathConfiguration {
    public static final String ROOT_PATH = "/var/www/html/";
    public static final String DOWNLOAD_PATH = ROOT_PATH + "/download/";
    public static final String UPLOAD_PATH = ROOT_PATH + "/upload/";
    public static final String LOG_PATH = DOWNLOAD_PATH + "logs/";
    public static final String FILE_PATH = DOWNLOAD_PATH + "files/";
}