package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.data.ChapterRequest;
import com.dgsaltarin.keromanga.domain.services.ChapterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    private ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/manga/{id}")
    public ResponseEntity<List<Chapter>> getMangaChapters(@PathVariable("id") int id) {
        return chapterService.getMangaChapters(id).map(chapters -> new ResponseEntity<>(chapters, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable("id") int id) {
        return chapterService.getChapterById(id).map(chapter -> new ResponseEntity<>(chapter, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Chapter> addChapter(@RequestParam("pages")MultipartFile[] pages, String request) {
        ChapterRequest chapterRequest;
        try {
            chapterRequest = new ObjectMapper().readValue(request, ChapterRequest.class);
            List<MultipartFile> pagesFile = Arrays.asList(pages);
            chapterRequest.setPages(pagesFile);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(chapterService.save(chapterRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        chapterService.delete(id);
        return new ResponseEntity( HttpStatus.OK);
    }
}
