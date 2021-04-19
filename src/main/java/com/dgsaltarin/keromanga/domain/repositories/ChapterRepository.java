package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository {

    Optional<List<Chapter>> getMangaChapters(int mangaId);
    Optional<Chapter> getChapter(int id);
    Chapter save(Chapter chapter);
    void delete(int id);
}
