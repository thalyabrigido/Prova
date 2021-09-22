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
        // Pedindo o repositorio para ir ao banco e me retorar todos os Musicas.
        List<Musica> Musicas = repositorioMusica.findAll();

        // Criando o nosso objeto para converter informações.
        ModelMapper mapper = new ModelMapper();

        // Aqui eu varro a lista de Musica e para cada Musica eu converto o mesmo
        // em MusicaDTO e ao final retorno esta lista.
        return Musicas.stream()
        .map(Musica -> mapper.map(Musica, MusicaDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO MusicaDto) {
   
        // Criando o nosso objeto para converter informações.
        ModelMapper mapper = new ModelMapper();

        // Convertendo o MusicaDto em um Musica model.
        Musica Musica = mapper.map(MusicaDto, Musica.class);

        // Removendo o id para forçar o cadastro.
        Musica.setId(null);

        // Salvando o Musica no banco, ou seja cadastrando.
        Musica = repositorioMusica.save(Musica);

        // Convertendo o Musica model em um MusicaDto para devolver.
        return mapper.map(Musica, MusicaDTO.class);
    }

    @Override
    public Optional<MusicaDTO> obterPorId(String idMusica) {
        
        // Tento encontrar um Musica pelo seu id.
        // Se encontrar ele me devolve um optional com o Musica dentro
        // Se não me devolve um optional vazio.
        Optional<Musica> optionalMusica = repositorioMusica.findById(idMusica);

        // Se não encontrar lanço uma exception.
        if(optionalMusica.isEmpty()){
            throw new InputMismatchException("Não foi encontrado o Musica com o id: " + idMusica);
        }

        // Tirando o Musica model de dentro do optional e convertendo ele para um MusicaDTO.
        MusicaDTO  MusicaDto = new ModelMapper().map(optionalMusica.get(), MusicaDTO.class);

       // Crio um otional de MusicaDto para devolver.
        return Optional.of(MusicaDto);
    }

    @Override
    public void delete(String idMusica) {
        // Aqui poderia criar uma logica para saber se o Musica existe com esse id.
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
