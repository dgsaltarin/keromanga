package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.data.MangaRequest;
import com.dgsaltarin.keromanga.domain.services.MangaService;
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
public final class MangaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private MangaController mangaController;
    @MockBean
    private MangaService mangaService;

    private Manga manga;
    List<Manga> mockList = new ArrayList<>();

    private String author;
    private String name;
    private String description;
    private String request;
    private String badRequest;
    private Manga manga2;

    @BeforeAll
    public void setUp() {
        mangaService = Mockito.mock(MangaService.class);
        mangaController = new MangaController(mangaService);
        manga = Mockito.mock(Manga.class);
        author = "Shinobu Ohtaka";
        name = "Magi: The Labyrinth of Magic";
        description = "La historia gira entorno a Aladdin, un misterioso niño de unos 10 años " +
                "que lleva consigo una flauta, con la cual es capaz de invocar a un djinn gigante llamado Ugo. ";
        manga2= new Manga();
        manga2.setName(name);
        manga2.setAuthor(author);
        manga2.setDescription(description);
        mockList.add(manga2);
        request = "{\n" +
                "  \"author\": \""+author+"\",\n" +
                "  \"name\":\"Magi: The Labyrinth of Magic\",\n" +
                "  \"description\": \"La historia gira entorno a Aladdin, un misterioso niño de unos 10 años que lleva consigo una flauta, con la cual es capaz de invocar a un djinn gigante llamado Ugo. \",\n" +
                "  \"onGoing\": false,\n" +
                "  \"tags\": [\n" +
                "    \"Fantasia\",\n" +
                "    \"Magia\"\n" +
                "  ]\n" +
                "}";
        badRequest = "{\n" +
                "  \"authors\": \""+author+"\",\n" +
                "  \"description\": \"La historia gira entorno a Aladdin, un misterioso niño de unos 10 años que lleva consigo una flauta, con la cual es capaz de invocar a un djinn gigante llamado Ugo. \",\n" +
                "  \"onGoing\": false,\n" +
                "  \"tags\": [\n" +
                "    \"Fantasia\",\n" +
                "    \"Magia\"\n" +
                "  ]\n" +
                "}";
        mockMvc = MockMvcBuilders.standaloneSetup(mangaController).build();
    }

    @Test
    public void shouldReturnNotFoundWhenNoMangaFound() {
        when(mangaService.getAll()).thenReturn(Optional.empty());
        ResponseEntity<List<Manga>> mangas = mangaController.getAll();
        assertEquals(HttpStatus.NOT_FOUND, mangas.getStatusCode());
    }

    @Test
    public void shouldReturnOkWhenMangaFound() {
        when(mangaService.getAll()).thenReturn(Optional.of(mockList));
        ResponseEntity<List<Manga>> mangas = mangaController.getAll();
        assertEquals(HttpStatus.OK, mangas.getStatusCode());
    }

    @Test
    public void shouldReturnOkWhenMangaFoundById() {
        when(mangaService.getManga(1)).thenReturn(Optional.of(manga));
        ResponseEntity<Manga> mangaResponse = mangaController.getMangaById(1);
        assertEquals(HttpStatus.OK, mangaResponse.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundWhenNotFoundMangaById() {
        when(mangaService.getManga(1)).thenReturn(Optional.empty());
        ResponseEntity<Manga> mangaResponse = mangaController.getMangaById(1);
        assertEquals(HttpStatus.NOT_FOUND, mangaResponse.getStatusCode());
    }

    @Test
    public void shouldReturnMangaWhenMangaIsCreatedSuccessfully() throws Exception {
        when(mangaService.save(any(MangaRequest.class))).thenReturn(manga2);
        mockMvc.perform(multipart("/manga/add")
                .file("cover", "cover".getBytes())
                .param("mangaRequestString", request)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(name)));
    }

    @Test
    public void shouldReturnBadRequestWhenBadRequestSendToAdd() throws Exception {
        mockMvc.perform(multipart("/manga/add")
                .file("cover", "cover".getBytes())
                .param("mangaRequestString", badRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ShouldReturnMangaWhenMangaFoundById() throws Exception {
        when(mangaService.getManga(any(Integer.class))).thenReturn(Optional.of(manga2));
        mockMvc.perform(get("/manga/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.author", is(author)))
                .andExpect(jsonPath("$.description", is(description)));
    }

    @Test
    public void shouldReturnNotFoundWhenNoMangaFoundById() throws Exception {
        when(mangaService.getManga(any(Integer.class))).thenReturn(Optional.empty());
        mockMvc.perform(get("/manga/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOkWhenMangaDeletedSuccessfully() throws Exception {
        when(mangaService.delete(any(Integer.class))).thenReturn(true);
        mockMvc.perform(delete("/manga/delete/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundWhenAtDeleteMangaDoNotExist() throws Exception {
        when(mangaService.delete(any(Integer.class))).thenReturn(false);
        mockMvc.perform(delete("/manga/delete/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void ShouldReturnMangaList() throws Exception {
        when(mangaService.getAll()).thenReturn(Optional.of(mockList));
        mockMvc.perform(get("/manga/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].author", is(author)))
                .andExpect(jsonPath("$[0].description", is(description)));
    }

    @Test
    public void shouldReturnNotFoundWhenGetAllFoundNothing() throws Exception {
        when(mangaService.getAll()).thenReturn(Optional.empty());
        mockMvc.perform(get("/manga/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}