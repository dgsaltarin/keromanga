package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.services.MangaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class MangaControllerTest {

    private MangaController mangaController;
    private MangaService mangaService;

    @BeforeAll
    public void setUp() {
        mangaService = Mockito.mock(MangaService.class);
        mangaController = new MangaController(mangaService);
    }

    @Test
    public void shouldReturnNotFoundWhenNoMangaFound() {


    }

    @Test
    public void shouldReturnOkWhenMangaFound() {
        Mockito.when(mangaService.getAll()).thenReturn(Mockito.any());
        ResponseEntity<List<Manga>> mangas = mangaController.getAll();
        Assertions.assertEquals(mangas.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNotFoundWhenMangaNotFoundById() {}

    @Test
    public void shouldReturnOkWhenFoundMangaById() {}

    @Test
    public void shouldReturnMangaWhenSaveSuccessful() {}

    @Test
    public void shouldReturnOKWhenDeleted() {}

    @Test void shouldReturnCREATEDWhenSaves() {}
}