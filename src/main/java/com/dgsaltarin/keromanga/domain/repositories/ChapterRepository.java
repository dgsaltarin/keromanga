package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Chapter;

public interface ChapterRepository {

    Chapter getChapter(int id);
    Chapter save(Chapter chapter);
    void delete(int id);
}
