package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.data.TagRequest;
import com.dgsaltarin.keromanga.domain.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<Tag> addTag(@RequestBody TagRequest tag) {
        return new ResponseEntity(tagService.add(tag), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam("id") int id) {
        tagService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
