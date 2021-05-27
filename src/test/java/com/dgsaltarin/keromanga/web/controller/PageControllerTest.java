package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Page;
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
class PageControllerTest {

    private PageController pageController;
    private ChapterService chapterService;
    private Page page;
    List<Page> mockList = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        chapterService = Mockito.mock(ChapterService.class);
        pageController = new PageController(chapterService);
        page = Mockito.mock(Page.class);
        mockList.add(page);
    }

    @Test
    public void shouldReturnOkWheNoPagesFound() {
        when(chapterService.getChapterPages(1)).thenReturn(Optional.of(mockList));
        ResponseEntity<List<Page>> pages = pageController.getChapterPages(1);
        assertEquals(HttpStatus.OK, pages.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundWhenNoPagesFound() {
        when(chapterService.getChapterPages(1)).thenReturn(Optional.empty());
        ResponseEntity<List<Page>> pages = pageController.getChapterPages(1);
        assertEquals(HttpStatus.NOT_FOUND, pages.getStatusCode());
    }

}