package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Chapter;
import com.dgsaltarin.keromanga.domain.data.ChapterRequest;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class ChapterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ChapterService chapterService;
    @InjectMocks
    private ChapterController chapterController;

    private Chapter chapter;
    private Chapter chapter2;
    private String request;
    private String badRequest;
    private int number;
    private int mangaId;
    private LocalDate date;
    List<Chapter> mockList = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        chapterService = Mockito.mock(ChapterService.class);
        chapterController = new ChapterController(chapterService);
        chapter = Mockito.mock(Chapter.class);
        number = 1;
        mangaId = 1;
        date = LocalDate.now();
        chapter2 = new Chapter();
        chapter2.setNumber(number);
        chapter2.setIdManga(mangaId);
        chapter2.setDate(date);
        request = "{\n" +
                "  \"number\": 1,\n" +
                "  \"date\":\"2021-04-13\",\n" +
                "  \"mangaId\": 1\n" +
                "}";
        badRequest = "{\n" +
                "  \"numbers\": 1,\n" +
                "  \"dat\":\"2021-04-13\",\n" +
                "}";
        mockList.add(chapter2);
        mockMvc = MockMvcBuilders.standaloneSetup(chapterController).build();
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
    public void shouldReturnChapterListByMangaId() throws Exception {
        when(chapterService.getMangaChapters(any(Integer.class))).thenReturn(Optional.of(mockList));
        mockMvc.perform(get("/chapter/manga/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number", is(number)))
                .andExpect(jsonPath("$[0].idManga", is(mangaId)));
    }

    @Test
    public void shouldReturnNotFoundWhenNoChaptersFoundByMangaId() throws Exception {
        when(chapterService.getMangaChapters(any(Integer.class))).thenReturn(Optional.empty());
        mockMvc.perform(get("/chapter/manga/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnChapterWhenIsCreatedSuccessfully() throws Exception {
        when(chapterService.save(any(ChapterRequest.class))).thenReturn(chapter2);
        mockMvc.perform(multipart("/chapter/add", 1)
                .file("pages", "pages".getBytes())
                .param("request", request)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", is(number)))
                .andExpect(jsonPath("$.idManga", is(mangaId)));
    }

    @Test
    public void shouldReturnBadRequestWhenBadRequestSentToAdd() throws Exception {
        when(chapterService.save(any(ChapterRequest.class))).thenReturn(chapter2);
        mockMvc.perform(multipart("/chapter/add", 1)
                .file("pages", "pages".getBytes())
                .param("request", badRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnChapterWhenChapterFoundById() throws Exception {
        when(chapterService.getChapterById(any(Integer.class))).thenReturn(Optional.of(chapter2));
        mockMvc.perform(get("/chapter/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(number)))
                .andExpect(jsonPath("$.idManga", is(mangaId)));
    }

    @Test
    public void shouldReturnNotFoundWhenChapterNotFoundById() throws Exception {
        when(chapterService.getChapterById(any(Integer.class))).thenReturn(Optional.empty());
        mockMvc.perform(get("/chapter/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOkWhenChapterDeleted() throws Exception {
        when(chapterService.delete(any(Integer.class))).thenReturn(true);
        mockMvc.perform(delete("/chapter/delete/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundWhenAtDeleteChapterIsNotFound() throws Exception {
        when(chapterService.delete(any(Integer.class))).thenReturn(false);
        mockMvc.perform(delete("/chapter/delete/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}