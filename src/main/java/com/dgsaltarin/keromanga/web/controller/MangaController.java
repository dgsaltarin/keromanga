package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Manga;
import com.dgsaltarin.keromanga.domain.data.MangaRequest;
import com.dgsaltarin.keromanga.domain.services.MangaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import java.util.List;

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

    @ApiOperation("Get a single manga by its id")
    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@ApiParam(value = "the manga id", required = true) @PathVariable("id") int id) {
        return mangaService.getManga(id).map(manga -> new ResponseEntity<>(manga, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation("add a new manga to database with no chapters")
    @PostMapping("/add")
    public ResponseEntity<Manga> addManga(@RequestParam("cover") MultipartFile file, String mangaRequestString) {
        MangaRequest mangaRequest;
        try {
            mangaRequest = new ObjectMapper().readValue(mangaRequestString, MangaRequest.class);
            mangaRequest.setCover(file);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(mangaService.save(mangaRequest), HttpStatus.CREATED);
    }

    @ApiOperation("delete manga by its id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        if (mangaService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
