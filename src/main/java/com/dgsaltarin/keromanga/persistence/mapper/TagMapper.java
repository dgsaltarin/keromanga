package com.dgsaltarin.keromanga.persistence.mapper;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.persistence.entity.TagEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
    Tag toTag(TagEntity tagEntity);

    TagEntity toTagEntity(Tag tag);

    List<Tag> toTags(List<TagEntity> tags);
}
