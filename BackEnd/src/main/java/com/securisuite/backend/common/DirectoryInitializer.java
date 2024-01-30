package com.securisuite.backend.common;

import com.securisuite.backend.common.config.PathConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryInitializer {
    @PostConstruct
    public void init() {
        createDirectory(PathConfiguration.DOWNLOAD_PATH);
        createDirectory(PathConfiguration.UPLOAD_PATH);
        createDirectory(PathConfiguration.LOG_PATH);
        createDirectory(PathConfiguration.FILE_PATH);
    }

    private void createDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
