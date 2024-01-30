package com.securisuite.backend.httrack.domain.command;

import com.securisuite.backend.httrack.domain.Httrack;
import com.securisuite.backend.httrack.domain.wget.Wget;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UrlCmd {
    private String url;

    public Httrack toHttrackEntity() {
        return Httrack.builder()
                .url(url)
                .build();
    }

    public Wget toWgetEntity() {
        return Wget.builder()
                .url(url)
                .build();
    }
}
