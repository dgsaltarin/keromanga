package com.dgsaltarin.keromanga.domain.converter;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.data.MangaRequest;
import com.dgsaltarin.keromanga.domain.services.TagService;
import com.dgsaltarin.keromanga.web.cloudinary.CloudinaryIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MangaConverter {

    private CloudinaryIntegration cloudinaryIntegration;
    private TagService tagService;

    @Autowired
    public MangaConverter(CloudinaryIntegration cloudinaryIntegration, TagService tagService) {
        this.cloudinaryIntegration = cloudinaryIntegration;
        this.tagService = tagService;
    }

    public Manga RequestMangaToManga(MangaRequest mangaRequest) {
        Manga manga = new Manga();
        List<Tag> tags = new ArrayList<>();

        for (String tagName: mangaRequest.getTags()) {
            tags.add(tagService.getTagByName(tagName));
        }

        manga.setName(mangaRequest.getName());
        manga.setAuthor(mangaRequest.getAuthor());
        manga.setDescription(mangaRequest.getDescription());
        manga.setTags(tags);
        manga.setOnGoing(mangaRequest.isOnGoing());
        manga.setCover(cloudinaryIntegration.uploadFile(mangaRequest.getCover()));
        return manga;
    }
}
