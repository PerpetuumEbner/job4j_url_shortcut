package ru.job4j.url_shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.dto.ShortUrlDTO;
import ru.job4j.url_shortcut.dto.StatisticsUrlDto;
import ru.job4j.url_shortcut.dto.UrlDTO;
import ru.job4j.url_shortcut.service.UrlService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody UrlDTO urlDTO) {
        ShortUrlDTO shortUrlDTO = new ShortUrlDTO();
        urlService.save(shortUrlDTO, urlDTO);
        return new ResponseEntity<>(shortUrlDTO, HttpStatus.OK);
    }

    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrl) {
        if (urlService.counterIncrease(shortUrl)) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("HTTP CODE - 302 REDIRECT URL", urlService.findByShortUrl(shortUrl).get().getUrl())
                    .build();
        }
        return new ResponseEntity<>("Такой ссылки не существует", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticsUrlDto>> statistic() {
        return new ResponseEntity<>(urlService.getStatistic(), HttpStatus.OK);
    }
}