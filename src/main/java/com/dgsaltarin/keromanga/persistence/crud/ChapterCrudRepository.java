package com.dgsaltarin.keromanga.persistence.crud;

import com.dgsaltarin.keromanga.persistence.entity.ChapterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChapterCrudRepository extends CrudRepository<ChapterEntity, Integer> {

    List<ChapterEntity> findAllByManga(int mangaId);
}
