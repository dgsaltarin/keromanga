package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, Integer> {
}
