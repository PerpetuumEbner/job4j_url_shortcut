package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.Website;
import ru.job4j.url_shortcut.repository.WebsiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WebsiteService {
    private final WebsiteRepository websiteRepository;

    public void save(Website website) {
        websiteRepository.save(website);
    }
}