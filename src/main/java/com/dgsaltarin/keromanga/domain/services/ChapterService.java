package com.dgsaltarin.keromanga.domain.services;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.converter.ChapterConverter;
import com.dgsaltarin.keromanga.domain.data.ChapterRequest;
import com.dgsaltarin.keromanga.domain.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    private ChapterRepository chapterRepository;
    private ChapterConverter chapterConverter;

    @Autowired
    public ChapterService(ChapterRepository chapterRepository, ChapterConverter chapterConverter) {
        this.chapterConverter = chapterConverter;
        this.chapterRepository = chapterRepository;
    }

    public Optional<List<Chapter>> getMangaChapters(int mangaId) {
        return chapterRepository.getMangaChapters(mangaId);
    }

    public Optional<Chapter> getChapterById(int id) {
        return chapterRepository.getChapter(id);
    }

    public Chapter save(ChapterRequest chapterRequest) {
        return chapterRepository.save(chapterConverter.convertChapterRequestToChapter(chapterRequest));
    }

    public void delete(int id) {
       chapterRepository.delete(id);
    }
}
