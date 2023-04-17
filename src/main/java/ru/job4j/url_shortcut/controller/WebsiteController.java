package ru.job4j.url_shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.dto.WebsiteDTO;
import ru.job4j.url_shortcut.dto.WebsiteResultDTO;
import ru.job4j.url_shortcut.model.Website;
import ru.job4j.url_shortcut.service.WebsiteService;

import static ru.job4j.url_shortcut.filter.Generator.valueGenerator;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class WebsiteController {
    private final WebsiteService websiteService;

    private final BCryptPasswordEncoder encoder;

    @PostMapping("/registration")
    public ResponseEntity<WebsiteResultDTO> registration(@RequestBody WebsiteDTO websiteDTO) {
        WebsiteResultDTO websiteResultDTO = new WebsiteResultDTO();
        Website website = new Website();
        String password = valueGenerator(12);
        website.setWebsite(websiteDTO.getWebsite());
        website.setLogin(websiteDTO.getWebsite());
        website.setPassword(encoder.encode(password));
        websiteResultDTO.setLogin(website.getLogin());
        websiteResultDTO.setPassword(password);
        try {
            websiteService.save(website);
            websiteResultDTO.setRegistration(true);
            return new ResponseEntity<>(websiteResultDTO, HttpStatus.OK);
        } catch (DataIntegrityViolationException exception) {
            return new ResponseEntity<>(websiteResultDTO, HttpStatus.OK);
        }
    }
}