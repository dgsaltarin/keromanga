package com.dgsaltarin.keromanga.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chapter")
public class ChapterEntity {

    private int id;
    private MangaEntity mangaEntity;
    private int number;
    private List<PageEntity> pages;
    private LocalDate date;
    private String cover;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    public List<PageEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageEntity> pageEntities) {
        this.pages = pageEntities;
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

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_manga")
    public MangaEntity getManga() {
        return mangaEntity;
    }

    public void setManga(MangaEntity mangaEntity) {
        this.mangaEntity = mangaEntity;
    }
}
