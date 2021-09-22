package com.tech4me.prova.view.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tech4me.prova.service.MusicaService;
import com.tech4me.prova.shared.MusicaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    
    @Autowired
    MusicaService servicoMusica;

    @GetMapping
    public ResponseEntity<List<MusicaDTO>> obterTodos(){
        // Aqui pego do meu serviço a lista de jogadores
        List<MusicaDTO> jogadores = servicoMusica.obterTodos();

        // Retorno uma resposta personalizada com a lista de jogadores e o statuscode ok.
        return new ResponseEntity<>(jogadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MusicaDTO>> obterPorId(@PathVariable String id){
        Optional<MusicaDTO> Musica = servicoMusica.obterPorId(id);

        return new ResponseEntity<>(Musica, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusicaDTO> adicionar(@RequestBody @Valid MusicaDTO MusicaDto ){
        // Aqui mando o serviço adicionar o novo Musica
        MusicaDto = servicoMusica.adicionar(MusicaDto);

        // Aqui devolvo a resposta para o usuario.
        return new ResponseEntity<>(MusicaDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoMusica.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaDTO> atualizar(@PathVariable String id, @RequestBody @Valid MusicaDTO MusicaDto){

        MusicaDTO dto = servicoMusica.atualizar(id, MusicaDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
