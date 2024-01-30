package com.securisuite.backend.httrack.interfaces.dto;


import com.securisuite.backend.httrack.domain.command.UrlCmd;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UrlRequest {
    @NotEmpty(message = "URL은 필수값입니다.")
    private String url;

    public UrlCmd toCommand() {
        return UrlCmd.builder()
                .url(url)
                .build();
    }
}
