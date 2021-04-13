package com.dgsaltarin.keromanga.persistence.crud;

import com.dgsaltarin.keromanga.persistence.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagCrudRepository extends CrudRepository<TagEntity, Integer> {

    TagEntity findByName(String name);
}
