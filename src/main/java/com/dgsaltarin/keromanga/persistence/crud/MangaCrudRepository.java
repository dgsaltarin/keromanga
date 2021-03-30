package com.dgsaltarin.keromanga.persistence.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dgsaltarin.keromanga.domain.*;

public interface MangaCrudRepository extends MongoRepository<Manga, Integer> {
}
