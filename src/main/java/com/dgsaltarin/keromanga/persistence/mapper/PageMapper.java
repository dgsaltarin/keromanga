package com.dgsaltarin.keromanga.persistence.mapper;

import com.dgsaltarin.keromanga.domain.Page;
import com.dgsaltarin.keromanga.persistence.entity.PageEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "url", target = "pageUrl"),
    })
    Page toPage(PageEntity pageEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "chapter", ignore = true),
    })
    PageEntity toPageEntity(Page page);

    List<Page> toPages(List<PageEntity> pages);
}
