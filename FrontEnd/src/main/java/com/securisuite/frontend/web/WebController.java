package com.securisuite.frontend.web;

import com.securisuite.frontend.config.PathConfig;
import com.securisuite.frontend.config.ViewConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WebController {
    private final WebFacade webFacade;

    @GetMapping("/")
    public String dashBoard(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("대시보드")
                .currentTab("Dashboard")
                .data(webFacade.getDashBoard())
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.VIEWS_PATH + "DashBoard";
    }

    @GetMapping("/tools")
    public String toolList(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("도구목록")
                .currentTab("Tool List")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.TOOLS_PATH + "ToolList";
    }

    @GetMapping("/tools/nmap")
    public String nmap(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("Nmap")
                .currentTab("Tool List")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.TOOLS_PATH + "Nmap";
    }

    @GetMapping("/tools/httrack")
    public String Httrack(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("Httrack")
                .currentTab("Tool List")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.TOOLS_PATH + "Httrack";
    }

    @GetMapping("/tools/crunch")
    public String crunch(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("Crunch")
                .currentTab("Tool List")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.TOOLS_PATH + "Crunch";
    }

    @GetMapping("/tools/john")
    public String john(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("John The Ripper")
                .currentTab("Tool List")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.TOOLS_PATH + "JohnTheRipper";
    }

    @GetMapping("/system")
    public String system(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("시스템명령")
                .currentTab("System")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.VIEWS_PATH + "System";
    }

    @GetMapping("/files")
    public String fileList(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("파일목록")
                .currentTab("files")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.VIEWS_PATH + "LogList";
    }

    @GetMapping("/logs")
    public String logList(Model model) {
        var viewConfig = ViewConfig.builder()
                .title("로그목록")
                .currentTab("logs")
                .build();
        model.addAttribute("viewConfig", viewConfig);
        return PathConfig.VIEWS_PATH + "FileList";
    }
}
