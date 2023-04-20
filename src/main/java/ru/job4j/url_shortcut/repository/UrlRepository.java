package ru.job4j.url_shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.url_shortcut.model.Url;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByUrl(String url);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Url SET counter = counter + 1 WHERE shortUrl = :shortUrl")
    void counterIncrease(@Param("shortUrl") String shortUrl);
}