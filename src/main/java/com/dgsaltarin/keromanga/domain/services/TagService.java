package com.dgsaltarin.keromanga.domain.services;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.converter.TagConverter;
import com.dgsaltarin.keromanga.domain.data.TagRequest;
import com.dgsaltarin.keromanga.domain.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;
    private TagConverter tagConverter;

    @Autowired
    public TagService(TagRepository tagRepository, TagConverter tagConverter) {
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
    }

    public Optional<List<Tag>> getAll() {
        return tagRepository.getAll();
    }

    public Tag add(TagRequest tag) {
        return tagRepository.save(tagConverter.convertTagRequestToTag(tag));
    }

    public void delete(int id) {
        tagRepository.delete(id);
    }
}
