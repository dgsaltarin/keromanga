package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PageRepository {

    Optional<List<Page>> getAll();
    Page save(Page page);
    void delete(int id);
}
