package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Optional<List<Tag>> getAll();
    Tag getByName(String name);
    Optional<Tag> getTagById(int id);
    Tag save(Tag tag);
    void delete(int id);
}
