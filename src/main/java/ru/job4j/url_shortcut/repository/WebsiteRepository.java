package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url_shortcut.model.Website;

import java.util.Optional;

@Repository
public interface WebsiteRepository extends CrudRepository<Website, Integer> {
    Optional<Website> findByWebsite(String website);

    Optional<Website> findByLogin(String username);
}