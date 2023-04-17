package ru.job4j.url_shortcut.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.dto.ShortUrlDTO;
import ru.job4j.url_shortcut.dto.StatisticsUrlDto;
import ru.job4j.url_shortcut.dto.UrlDTO;
import ru.job4j.url_shortcut.model.Url;
import ru.job4j.url_shortcut.service.UrlService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.job4j.url_shortcut.filter.Generator.valueGenerator;

@AllArgsConstructor
@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlService urlService;

    private final ModelMapper modelMapper;

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody UrlDTO urlDTO) {
        ShortUrlDTO shortUrlDTO = new ShortUrlDTO();
        String shortUrl = valueGenerator(7);
        Url url = new Url();
        url.setUrl(urlDTO.getUrl());
        url.setShortUrl(shortUrl);
        url.setCounter(0);
        try {
            urlService.save(url);
            shortUrlDTO.setShortUrl(shortUrl);
        } catch (DataIntegrityViolationException exception) {
            return new ResponseEntity<>("Ссылка уже есть в базе данных!", HttpStatus.OK);
        }
        return new ResponseEntity<>(shortUrlDTO, HttpStatus.OK);
    }

    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrl) {
        Optional<Url> url = urlService.findByShortUrl(shortUrl);
        if (url.isPresent()) {
            urlService.counterIncrease(shortUrl);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("HTTP CODE - 302 REDIRECT URL", url.get().getUrl())
                    .build();
        }
        return new ResponseEntity<>("Такой ссылки не существует", HttpStatus.OK);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticsUrlDto>> statistic() {
        List<StatisticsUrlDto> list = new ArrayList<>();
        for (Url url : urlService.findAll()) {
            StatisticsUrlDto statisticsUrlDto = convertToStatisticsUrlDto(url);
            list.add(statisticsUrlDto);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public StatisticsUrlDto convertToStatisticsUrlDto(Url url) {
        return modelMapper.map(url, StatisticsUrlDto.class);
    }
}