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
}
