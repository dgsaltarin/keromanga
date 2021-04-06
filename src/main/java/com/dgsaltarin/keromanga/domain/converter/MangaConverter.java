package com.dgsaltarin.keromanga.domain.converter;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.data.MangaRequest;
import com.dgsaltarin.keromanga.web.cloudinary.CloudinaryIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MangaConverter {

    private CloudinaryIntegration cloudinaryIntegration;

    @Autowired
    public MangaConverter(CloudinaryIntegration cloudinaryIntegration) {
        this.cloudinaryIntegration = cloudinaryIntegration;
    }

    public Manga RequestMangaToManga(MangaRequest mangaRequest) {
        Manga manga = new Manga();
        manga.setAuthor(mangaRequest.getAuthor());
        manga.setDescription(mangaRequest.getDescription());
        manga.setTags(mangaRequest.getTags());
        manga.setOnGoing(mangaRequest.isOnGoing());
        manga.setCover(cloudinaryIntegration.uploadFile(mangaRequest.getCover()));
        return manga;
    }
}
