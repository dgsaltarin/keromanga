package com.dgsaltarin.keromanga.persistence;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.repositories.MangaRepository;
import com.dgsaltarin.keromanga.persistence.crud.MangaCrudRepository;
import com.dgsaltarin.keromanga.persistence.entity.MangaEntity;
import com.dgsaltarin.keromanga.persistence.mapper.MangaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class MangaEntityRepository implements MangaRepository {

    private MangaCrudRepository mangaCrudRepository;
    private MangaMapper mangaMapper;

    @Autowired
    public MangaEntityRepository(MangaCrudRepository mangaCrudRepository, MangaMapper mangaMapper) {
        this.mangaCrudRepository = mangaCrudRepository;
        this.mangaMapper = mangaMapper;
    }

    @Override
    public Optional<List<Manga>> getAll() {
        List<MangaEntity> mangas = (List<MangaEntity>) mangaCrudRepository.findAll();
        return Optional.of(mangaMapper.toMangas(mangas));
    }

    @Override
    public Optional<Manga> getManga(int id) {
        Manga manga = null;
        try {
            manga = mangaMapper.toManga(mangaCrudRepository.findById(id).get());
        } catch (NoSuchElementException e) {}
        return Optional.ofNullable(manga);
    }

    @Override
    public Manga save(Manga manga) {
        MangaEntity mangaEntity = mangaMapper.toMangaEntity(manga);
        return mangaMapper.toManga(mangaCrudRepository.save(mangaEntity));
    }

    @Override
    public void delete(int id) {
        mangaCrudRepository.deleteById(id);
    }
}
