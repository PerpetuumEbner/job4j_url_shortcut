package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.dto.WebsiteDTO;
import ru.job4j.url_shortcut.dto.WebsiteResultDTO;
import ru.job4j.url_shortcut.model.Website;
import ru.job4j.url_shortcut.repository.WebsiteRepository;

import java.util.Optional;

import static ru.job4j.url_shortcut.filter.Generator.valueGenerator;

@Service
@AllArgsConstructor
public class WebsiteService {
    private final WebsiteRepository websiteRepository;

    private final BCryptPasswordEncoder encoder;

    public boolean checkWebsiteEmpty(WebsiteDTO websiteDTO) {
        Optional<Website> optionalWebsiteDTO = websiteRepository.findByWebsite(websiteDTO.getWebsite());
        return optionalWebsiteDTO.isEmpty();
    }

    public WebsiteResultDTO successReg(WebsiteDTO websiteDTO) {
        WebsiteResultDTO websiteResultDTO = new WebsiteResultDTO();
        String password = valueGenerator(12);
        String login = valueGenerator(7);
        Website website = Website.builder()
                .website(websiteDTO.getWebsite())
                .login(login)
                .password(encoder.encode(password)).build();
        websiteRepository.save(website);
        websiteResultDTO.setRegistration(true);
        websiteResultDTO.setLogin(login);
        websiteResultDTO.setPassword(password);
        return websiteResultDTO;
    }
}