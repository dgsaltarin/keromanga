package com.dgsaltarin.keromanga.persistence;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.repositories.ChapterRepository;
import com.dgsaltarin.keromanga.persistence.crud.ChapterCrudRepository;
import com.dgsaltarin.keromanga.persistence.entity.ChapterEntity;
import com.dgsaltarin.keromanga.persistence.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChapterEntityRepository implements ChapterRepository {

    private ChapterCrudRepository chapterCrudRepository;
    private ChapterMapper chapterMapper;

    @Autowired
    public ChapterEntityRepository(ChapterCrudRepository chapterCrudRepository, ChapterMapper chapterMapper) {
        this.chapterCrudRepository = chapterCrudRepository;
        this.chapterMapper = chapterMapper;
    }

    @Override
    public Chapter getChapter(int id) {
        return chapterMapper.toChapter(chapterCrudRepository.findById(id).get());
    }

    @Override
    public Chapter save(Chapter chapter) {
        ChapterEntity chapterEntity = chapterMapper.toChapterEntity(chapter);
        return chapterMapper.toChapter(chapterCrudRepository.save(chapterEntity));
    }

    @Override
    public void delete(int id) {
        chapterCrudRepository.deleteById(id);
    }
}
