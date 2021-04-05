package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.repositories.MangaRepository;
import com.dgsaltarin.keromanga.domain.services.MangaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manga")
public class MangaController {


    private MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @ApiOperation("Get all mangas in bada base")
    @GetMapping("/all")
    public ResponseEntity<List<Manga>> getAll() {
        return mangaService.getAll()
                .map(mangas -> new ResponseEntity<>(mangas, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
