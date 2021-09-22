package com.tech4me.prova.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.prova.shared.MusicaDTO;




public interface MusicaService {
    
    List<MusicaDTO> obterTodos();

    Optional<MusicaDTO> obterPorId(String idMusica);

    MusicaDTO adicionar(MusicaDTO musicaDto);

    MusicaDTO atualizar(String id, MusicaDTO musicaDto);

    void delete(String idMUsica);

}
