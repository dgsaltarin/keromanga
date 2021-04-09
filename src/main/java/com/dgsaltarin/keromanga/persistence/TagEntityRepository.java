package com.dgsaltarin.keromanga.persistence;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.repositories.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagEntityRepository implements TagRepository {
    @Override
    public Optional<List<Tag>> getAll() {
        return Optional.empty();
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
