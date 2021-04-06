package com.dgsaltarin.keromanga.domain.data;

import com.dgsaltarin.keromanga.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class ChapterRequest {

    private int number;
    private List<MultipartFile> pages;
    private LocalDate date;
    private String cover;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<MultipartFile> getPages() {
        return pages;
    }

    public void setPages(List<MultipartFile> pages) {
        this.pages = pages;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
