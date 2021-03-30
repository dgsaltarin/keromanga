package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MangaRepository extends MongoRepository<Manga, Integer> {

    List<Manga> getAll();
    Manga getById(int id);
    Manga getRandom();
    List<Manga> getByTag(Tag tag);
    List<Manga> getByTags(List<Tag> tags);
    Manga save(Manga manga);
    void deleteById(int id);
}
