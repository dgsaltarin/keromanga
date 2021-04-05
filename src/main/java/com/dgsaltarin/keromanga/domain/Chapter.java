package com.dgsaltarin.keromanga.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
public class Chapter {

    @Id
    private int number;
    private List<Page> pages;
    private LocalDate date;
    private String cover;
}
