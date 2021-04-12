package com.dgsaltarin.keromanga.persistence;

import com.dgsaltarin.keromanga.domain.Page;
import com.dgsaltarin.keromanga.domain.repositories.PageRepository;
import com.dgsaltarin.keromanga.persistence.crud.PageCrudRepository;
import com.dgsaltarin.keromanga.persistence.entity.PageEntity;
import com.dgsaltarin.keromanga.persistence.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PageEntityRepository implements PageRepository {

    private PageCrudRepository pageCrudRepository;
    private PageMapper pageMapper;

    @Autowired
    public PageEntityRepository(PageCrudRepository pageCrudRepository, PageMapper pageMapper) {
        this.pageCrudRepository = pageCrudRepository;
        this.pageMapper = pageMapper;
    }
    @Override
    public Optional<List<Page>> getChapterPages(int idChapter) {
        return Optional.empty();
    }

    @Override
    public Page save(Page page) {
        PageEntity pageEntity = pageMapper.toPageEntity(page);
        return pageMapper.toPage(pageCrudRepository.save(pageEntity));
    }
}
