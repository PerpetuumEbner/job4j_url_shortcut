package ru.job4j.url_shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WebsiteResultDTO {
    private boolean registration;

    private String login;

    private String password;
}