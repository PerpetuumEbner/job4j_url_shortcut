package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.dto.ShortUrlDTO;
import ru.job4j.url_shortcut.dto.StatisticsUrlDto;
import ru.job4j.url_shortcut.dto.UrlDTO;
import ru.job4j.url_shortcut.model.Url;
import ru.job4j.url_shortcut.repository.UrlRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.job4j.url_shortcut.filter.Generator.valueGenerator;

@Service
@AllArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    private final ModelMapper modelMapper;

    public void save(ShortUrlDTO shortUrlDTO, UrlDTO urlDTO) {
        String shortUrl = valueGenerator(7);
        Url url = Url.builder()
                .url(urlDTO.getUrl())
                .shortUrl(shortUrl)
                .counter(0).build();
        Optional<Url> urlOptional = urlRepository.findByUrl(url.getUrl());
        if (urlOptional.isPresent()) {
            throw new DataIntegrityViolationException("Ссылка уже есть в базе данных!");
        }
        urlRepository.save(url);
        shortUrlDTO.setShortUrl(shortUrl);
    }

    public Optional<Url> findByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    public boolean counterIncrease(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
        if (url.isEmpty()) {
            return false;
        }
        urlRepository.counterIncrease(shortUrl);
        return true;
    }

    public List<StatisticsUrlDto> getStatistic() {
        List<StatisticsUrlDto> list = new ArrayList<>();
        for (Url url : urlRepository.findAll()) {
            StatisticsUrlDto statisticsUrlDto = convertToStatisticsUrlDto(url);
            list.add(statisticsUrlDto);
        }
        return list;
    }

    public StatisticsUrlDto convertToStatisticsUrlDto(Url url) {
        return modelMapper.map(url, StatisticsUrlDto.class);
    }
}