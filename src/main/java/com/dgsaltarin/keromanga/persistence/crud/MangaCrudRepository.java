package com.dgsaltarin.keromanga.persistence.crud;

import com.dgsaltarin.keromanga.persistence.entity.MangaEntity;
import org.springframework.data.repository.CrudRepository;

public interface MangaCrudRepository extends CrudRepository<MangaEntity, Integer> {

}
