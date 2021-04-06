package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.data.TagRequest;
import com.dgsaltarin.keromanga.domain.services.TagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tag>> getAll() {
        return tagService.getAll().map(tags -> new ResponseEntity<>(tags, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity addTag(@RequestParam("name") String name) {
        TagRequest tagRequest;
        try {
            tagRequest = new ObjectMapper().readValue(name, TagRequest.class);
            tagService.add(tagRequest);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") int id) {
        tagService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
