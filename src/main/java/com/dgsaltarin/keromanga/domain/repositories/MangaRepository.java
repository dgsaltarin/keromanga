package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Manga;

import java.util.List;
import java.util.Optional;


public interface MangaRepository {

    Optional<List<Manga>> getAll();
    Manga save(Manga manga);
    void delete(int id);
}
