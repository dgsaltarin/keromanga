package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Page;
import com.dgsaltarin.keromanga.domain.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {

    private ChapterService chapterService;

    @Autowired
    public PageController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapter/{id}")
    public ResponseEntity<List<Page>> getChapterPages(@PathVariable("id") int id) {
        return chapterService.getChapterPages(id).map(pages -> new ResponseEntity<>(pages, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
