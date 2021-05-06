package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.data.MangaRequest;
import com.dgsaltarin.keromanga.domain.services.MangaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class MangaControllerTest {

    private MangaController mangaController;
    private MangaService mangaService;
    private Manga manga;
    private MangaRequest mangaRequest;
    private MultipartFile cover;
    List<Manga> mockList = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        mangaService = Mockito.mock(MangaService.class);
        mangaController = new MangaController(mangaService);
        manga = Mockito.mock(Manga.class);
        mockList.add(manga);
        mangaRequest = Mockito.mock(MangaRequest.class);
        cover = Mockito.mock(MultipartFile.class);
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
}