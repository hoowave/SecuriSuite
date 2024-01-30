package com.securisuite.backend.common.service;

import com.securisuite.backend.common.config.PathConfiguration;
import com.securisuite.backend.common.exception.BaseException;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ShellCommandExecutor {
    private final String logPath = PathConfiguration.LOG_PATH;
    // 쉘 명령만 수행
    public boolean execute(String[] cmd) {
        try {
            // 쉘 명령 수행
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 쉘 명령 수행 + 로그 생성
    public boolean execute(String[] cmd, String logName) {
        try {
            // 쉘 명령 수행
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 로그 파일 저장
            File outputFile = new File(logPath + logName);
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = process.getInputStream();

            int readByte;
            while ((readByte = is.read()) != -1) {
                fos.write(readByte);
            }
            return true;
        } catch (Exception e) {
            throw new BaseException("내부 서버 에러가 발생했습니다.");
        }
    }

    // 로그 파일명을 통해 로그 내용을 전달받음
    public String getLog(String logName) {
        StringBuilder logContent = new StringBuilder();
        try {
            // 로그 생성될 시간을 고려하여 1초의 텀을 주고
            Thread.sleep(1000);
            // 로그 파일 열기
            File inputFile = new File(logPath + logName);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line;
            while ((line = reader.readLine()) != null) {
                logContent.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new BaseException("로그 파일을 읽는 도중 에러가 발생했습니다.");
        }
        return logContent.toString();
    }
}
