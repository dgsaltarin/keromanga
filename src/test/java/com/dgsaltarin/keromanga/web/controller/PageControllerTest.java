package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Page;
import com.dgsaltarin.keromanga.domain.services.ChapterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private PageController pageController;
    @MockBean
    private ChapterService chapterService;

    private Page page;
    private Page page2;
    private String url;
    List<Page> mockList = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        chapterService = Mockito.mock(ChapterService.class);
        pageController = new PageController(chapterService);
        url = "page url";
        page2 = new Page();
        page2.setPageUrl(url);
        page = Mockito.mock(Page.class);
        mockList.add(page2);
        mockMvc = MockMvcBuilders.standaloneSetup(pageController).build();
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

    @Test
    public void shouldReturnPagesWhenFoundByChapterId() throws Exception {
        when(chapterService.getChapterPages(any(Integer.class))).thenReturn(Optional.of(mockList));
        mockMvc.perform(get("/page/chapter/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pageUrl", is(url)));
    }

    @Test
    public void shouldReturnNotFoundWhenNoPagesFoundByChapterId() throws Exception {
        when(chapterService.getChapterPages(any(Integer.class))).thenReturn(Optional.empty());
        mockMvc.perform(get("/page/chapter/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}