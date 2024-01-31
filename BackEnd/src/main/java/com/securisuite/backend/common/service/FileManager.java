package com.securisuite.backend.common.service;

import com.securisuite.backend.common.config.PathConfig;
import com.securisuite.backend.common.exception.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileManager {
    private final String uploadPath = PathConfig.UPLOAD_PATH;
    private final String logPath = PathConfig.LOG_PATH;
    private final String filePath = PathConfig.FILE_PATH;

    public boolean isFileExist(String fileName) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash", "-c", "test -f \"" + filePath + fileName + "\"");

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogExist(String logName) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash", "-c", "test -f \"" + logPath + logName + "\"");

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadFile(MultipartFile file, String fileName) {
        if (file.isEmpty()) throw new BaseException("파일이 존재하지 않습니다.");
        File targetFile = new File(uploadPath, fileName);
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            throw new IllegalStateException("파일 업로드중 에러가 발생했습니다.");
        }
    }

    public void deleteLog(String logName) {
        File logFile = new File(logPath + logName);
        try {
            logFile.delete();
        } catch (Exception e) {
            throw new BaseException("내부 서버 에러가 발생했습니다.");
        }
    }
}
