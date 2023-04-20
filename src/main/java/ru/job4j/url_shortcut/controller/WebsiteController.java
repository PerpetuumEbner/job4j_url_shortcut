package ru.job4j.url_shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.dto.WebsiteDTO;
import ru.job4j.url_shortcut.dto.WebsiteRegistrationDTO;
import ru.job4j.url_shortcut.service.WebsiteService;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class WebsiteController {
    private final WebsiteService websiteService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody WebsiteDTO websiteDTO) {
        if (websiteService.checkWebsiteEmpty(websiteDTO)) {
            return new ResponseEntity<>(websiteService.successReg(websiteDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(new WebsiteRegistrationDTO(), HttpStatus.CONFLICT);
    }
}