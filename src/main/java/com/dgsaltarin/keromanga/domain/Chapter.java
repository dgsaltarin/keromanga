package com.dgsaltarin.keromanga.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Chapter {

    private List<Page> pages;
    private LocalDate date;
    private int number;
    private String cover;
}
