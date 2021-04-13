package com.dgsaltarin.keromanga.domain.converter;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.Page;
import com.dgsaltarin.keromanga.domain.data.ChapterRequest;
import com.dgsaltarin.keromanga.domain.services.MangaService;
import com.dgsaltarin.keromanga.web.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChapterConverter {

    private CloudinaryService cloudinaryService;
    private MangaService mangaService;

    @Autowired
    public ChapterConverter (CloudinaryService cloudinaryService, MangaService mangaService) {
        this.cloudinaryService = cloudinaryService;
        this.mangaService = mangaService;
    }

    public Chapter convertChapterRequestToChapter(ChapterRequest chapterRequest) {
        Manga manga = mangaService.getManga(chapterRequest.getMangaId()).get();
        List<Page> pages = new ArrayList<>();
        for (MultipartFile page: chapterRequest.getPages()) {
            Page chapterPage = new Page();
            String pageUrl = cloudinaryService.uploadFile(page);
            chapterPage.setPageUrl(pageUrl);
            pages.add(chapterPage);
        }
        Chapter chapter = new Chapter();
        chapter.setManga(manga);
        chapter.setDate(chapterRequest.getDate());
        chapter.setNumber(chapterRequest.getNumber());
        chapter.setCover(pages.get(1).getPageUrl());
        return chapter;
    }
}
