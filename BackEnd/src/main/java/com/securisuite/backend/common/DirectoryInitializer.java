package com.securisuite.backend.common;

import com.securisuite.backend.common.config.PathConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryInitializer {
    @PostConstruct
    public void init() {
        createDirectory(PathConfig.DOWNLOAD_PATH);
        createDirectory(PathConfig.UPLOAD_PATH);
        createDirectory(PathConfig.LOG_PATH);
        createDirectory(PathConfig.FILE_PATH);
    }

    private void createDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
