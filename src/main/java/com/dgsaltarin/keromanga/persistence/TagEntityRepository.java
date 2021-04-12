package com.dgsaltarin.keromanga.persistence;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.repositories.TagRepository;
import com.dgsaltarin.keromanga.persistence.crud.TagCrudRepository;
import com.dgsaltarin.keromanga.persistence.entity.TagEntity;
import com.dgsaltarin.keromanga.persistence.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagEntityRepository implements TagRepository {

    private TagCrudRepository tagCrudRepository;
    private TagMapper tagMapper;

    @Autowired
    public TagEntityRepository(TagCrudRepository tagCrudRepository, TagMapper tagMapper) {
        this.tagCrudRepository = tagCrudRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public Optional<List<Tag>> getAll() {
        List<TagEntity> tags = (List<TagEntity>) tagCrudRepository.findAll();
        return Optional.of(tagMapper.toTags(tags));
    }

    @Override
    public Tag save(Tag tag) {
        TagEntity tagEntity = tagCrudRepository.save(tagMapper.toTagEntity(tag));
        return tagMapper.toTag(tagEntity);
    }

    @Override
    public void delete(int id) {
        tagCrudRepository.deleteById(id);
    }
}
