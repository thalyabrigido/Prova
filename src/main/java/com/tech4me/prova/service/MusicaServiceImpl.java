package com.tech4me.prova.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tech4me.prova.model.Musica;
import com.tech4me.prova.repository.MusicaRepository;
import com.tech4me.prova.shared.MusicaDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    MusicaRepository repositorioMusica;

    @Override
    public List<MusicaDTO> obterTodos() {
        
        List<Musica> Musicas = repositorioMusica.findAll();

        
        ModelMapper mapper = new ModelMapper();

        
        
        return Musicas.stream()
        .map(Musica -> mapper.map(Musica, MusicaDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO MusicaDto) {
   
        
        ModelMapper mapper = new ModelMapper();

        
        Musica Musica = mapper.map(MusicaDto, Musica.class);

        
        Musica.setId(null);

        
        Musica = repositorioMusica.save(Musica);

        
        return mapper.map(Musica, MusicaDTO.class);
    }

    @Override
    public Optional<MusicaDTO> obterPorId(String idMusica) {
        
        
        
        
        Optional<Musica> optionalMusica = repositorioMusica.findById(idMusica);

        
        if(optionalMusica.isEmpty()){
            throw new InputMismatchException("Não foi encontrado o Musica com o id: " + idMusica);
        }

        
        MusicaDTO  MusicaDto = new ModelMapper().map(optionalMusica.get(), MusicaDTO.class);

       
        return Optional.of(MusicaDto);
    }

    @Override
    public void delete(String idMusica) {
        
        repositorioMusica.deleteById(idMusica);
    }

    @Override
    public MusicaDTO atualizar(String id, MusicaDTO MusicaDto) {
                
        MusicaDto.setId(id);

        Musica Musica = new ModelMapper().map(MusicaDto, Musica.class);
        repositorioMusica.save(Musica);

        return MusicaDto;
    }
    
}
