package com.dgsaltarin.keromanga.domain.repositories;

import com.dgsaltarin.keromanga.domain.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MangaRepository extends MongoRepository<Manga, Integer> {

}