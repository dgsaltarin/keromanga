package com.dgsaltarin.keromanga.domain;

import lombok.Data;

import java.util.List;

@Data
public class Manga {

    private int id;
    private String author;
    private String cover;
    private List<Chapter> chapters;
    private String description;
    private boolean onGoing;
    private List<Tag> tags;
}
