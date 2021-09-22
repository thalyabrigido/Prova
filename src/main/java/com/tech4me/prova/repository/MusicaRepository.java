package com.tech4me.prova.repository;

import com.tech4me.prova.model.Musica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
