package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.services.ChapterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChapterControllerTest {

    private ChapterController chapterController;
    private ChapterService chapterService;
    private Chapter chapter;
    List<Chapter> mockList = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        chapterService = Mockito.mock(ChapterService.class);
        chapterController = new ChapterController(chapterService);
        chapter = Mockito.mock(Chapter.class);
        mockList.add(chapter);
    }

    @Test
    public void shouldReturnNotFoundWhenNoChapterFoundById() {
        when(chapterService.getChapterById(1)).thenReturn(Optional.empty());
        ResponseEntity<Chapter> chapter = chapterController.getChapterById(1);
        assertEquals(HttpStatus.NOT_FOUND, chapter.getStatusCode());
    }

    @Test
    public void shouldReturnOkWhenChapterFoundById() {
        when(chapterService.getChapterById(1)).thenReturn(Optional.of(chapter));
        ResponseEntity<Chapter> chapter = chapterController.getChapterById(1);
        assertEquals(HttpStatus.OK, chapter.getStatusCode());
    }

    @Test
    public void shouldReturnOkWhenMangasFoundByMangaId() {
        when(chapterService.getMangaChapters(1)).thenReturn(Optional.of(mockList));
        ResponseEntity<List<Chapter>> chapters = chapterController.getMangaChapters(1);
        assertEquals(HttpStatus.OK, chapters.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundWhenNotFoundChaptersByMangaId() {
        when(chapterService.getMangaChapters(1)).thenReturn(Optional.empty());
        ResponseEntity<List<Chapter>> chapters = chapterController.getMangaChapters(1);
        assertEquals(HttpStatus.NOT_FOUND, chapters.getStatusCode());
    }
}