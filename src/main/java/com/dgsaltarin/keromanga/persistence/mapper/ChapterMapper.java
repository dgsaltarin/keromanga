package com.dgsaltarin.keromanga.persistence.mapper;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.persistence.entity.ChapterEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "cover", target = "cover"),
            @Mapping(source = "pages", target = "pages"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "manga", target = "manga"),
    })
    Chapter toChapter(ChapterEntity chapterEntity);

    @InheritInverseConfiguration
    ChapterEntity toChapterEntity(Chapter chapter);

    List<Chapter> toChapters(List<ChapterEntity> chapters);
}
