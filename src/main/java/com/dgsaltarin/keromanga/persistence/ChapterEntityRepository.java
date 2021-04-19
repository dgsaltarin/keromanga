package com.dgsaltarin.keromanga.persistence;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.Page;
import com.dgsaltarin.keromanga.domain.repositories.ChapterRepository;
import com.dgsaltarin.keromanga.persistence.crud.ChapterCrudRepository;
import com.dgsaltarin.keromanga.persistence.entity.ChapterEntity;
import com.dgsaltarin.keromanga.persistence.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChapterEntityRepository implements ChapterRepository {

    private ChapterCrudRepository chapterCrudRepository;
    private ChapterMapper chapterMapper;
    private PageEntityRepository pageEntityRepository;

    @Autowired
    public ChapterEntityRepository(ChapterCrudRepository chapterCrudRepository, ChapterMapper chapterMapper, PageEntityRepository pageEntityRepository) {
        this.chapterCrudRepository = chapterCrudRepository;
        this.chapterMapper = chapterMapper;
        this.pageEntityRepository = pageEntityRepository;
    }

    @Override
    public Optional<List<Chapter>> getMangaChapters(int mangaId) {
        List<ChapterEntity> chapters = chapterCrudRepository.findAllByManga(mangaId);
        return Optional.ofNullable(chapterMapper.toChapters(chapters));
    }

    @Override
    public Optional<Chapter> getChapter(int id) {
        return Optional.of(chapterMapper.toChapter(chapterCrudRepository.findById(id).get()));
    }

    @Override
    public Chapter save(Chapter chapter) {
        ChapterEntity chapterEntity = chapterMapper.toChapterEntity(chapter);
        Chapter chapterSaved = chapterMapper.toChapter(chapterCrudRepository.save(chapterEntity));
        /*for (Page page: chapter.getPages()) {
            page.setChapter(chapterSaved);
            pageEntityRepository.save(page);
        }*/
        return chapterSaved;
    }

    @Override
    public void delete(int id) {
        chapterCrudRepository.deleteById(id);
    }

}
