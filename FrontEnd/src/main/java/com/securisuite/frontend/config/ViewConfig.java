package com.securisuite.frontend.config;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ViewConfig {
    private String title;
    private String currentTab;

    @Builder
    public ViewConfig(String title, String currentTab){
        this.title = title;
        this.currentTab = currentTab;
    }
}
