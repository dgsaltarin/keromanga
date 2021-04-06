package com.dgsaltarin.keromanga.domain.services;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.converter.MangaConverter;
import com.dgsaltarin.keromanga.domain.data.MangaRequest;
import com.dgsaltarin.keromanga.domain.repositories.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MangaService {

    private MangaRepository mangaRepository;
    private MangaConverter mangaConverter;

    @Autowired
    public MangaService(MangaRepository mangaRepository, MangaConverter mangaConverter) {
        this.mangaRepository = mangaRepository;
        this.mangaConverter = mangaConverter;
    }

    public Optional<List<Manga>> getAll() {
        return Optional.ofNullable(mangaRepository.findAll());
    }

    public Manga save(MangaRequest mangaRequest) {
        return mangaRepository.save(mangaConverter.RequestMangaToManga(mangaRequest));
    }
}
