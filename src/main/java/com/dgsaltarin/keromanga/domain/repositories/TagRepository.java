package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Optional<List<Tag>> getAll();
    Tag save(Tag tag);
    void delete(int id);
}
