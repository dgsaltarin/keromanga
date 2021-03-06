package com.dgsaltarin.keromanga.persistence.mapper;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.persistence.entity.MangaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface MangaMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "cover", target = "cover"),
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "onGoing", target = "onGoing"),
            @Mapping(source = "tags", target = "tags"),
    })
    Manga toManga(MangaEntity mangaEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "chapters", ignore = true),
    })
    MangaEntity toMangaEntity(Manga manga);

    List<Manga> toMangas(List<MangaEntity> mangas);
}
