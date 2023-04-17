package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.Url;
import ru.job4j.url_shortcut.repository.UrlRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public void save(Url url) {
        urlRepository.save(url);
    }

    public Optional<Url> findByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    public void counterIncrease(String shortUrl) {
        urlRepository.counterIncrease(shortUrl);
    }
}