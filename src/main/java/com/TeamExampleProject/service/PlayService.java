package com.TeamExampleProject.service;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.searchCriteria.Document;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.PlayDto;
import com.TeamExampleProject.repo.PlayRepo;
import com.TeamExampleProject.service.serviceImpl.PlayServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayService implements PlayServiceImpl {
    @Autowired
    private PlayRepo playRepo;

    @Override
    public ResponseEntity<String> createPlay(PlayDto playDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Play play = mapper.map(playDto, Play.class);
        playRepo.save(play);
        return new ResponseEntity<>("Play Created Succesfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Play>> getAllPlay() {
        List<Play> play = new ArrayList<>();
        if (play != null) {
            playRepo.findAll().forEach(play::add);
        } else if (play == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(play, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GenericDTO> createPlayByResponseEntity(PlayDto playDto) {
        ModelMapper mapper = new ModelMapper();
        GenericDTO gene = new GenericDTO();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Play play = mapper.map(playDto, Play.class);
        gene.setObject(play);
        gene.setMessage("Play Created Succesfully");
        playRepo.save(play);
        return new ResponseEntity<>(gene, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getByIdPlay(Long id) {
        Optional<Play> play = playRepo.findById(id);
        GenericDTO dto = new GenericDTO();
        dto.setObject(play);
        dto.setMessage("Your " + id + " ID Details Shown Above");
        if (play.isPresent()) {
            return new ResponseEntity<Object>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<Object> updatePlayById(Long id, PlayDto playDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Play play = mapper.map(playDto, Play.class);
        GenericDTO dto = new GenericDTO();
        dto.setObject(play);
        dto.setMessage("Given " + id + " Id has Updated Succesfully");
        playRepo.save(play);
        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deletePlayById(Long id) {
        playRepo.deleteById(id);
        return new ResponseEntity<String>("Deleted Succesfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAllPlay() {
        playRepo.deleteAll();
        return new ResponseEntity<String>("All PLay has Deleted", HttpStatus.OK);
    }

}

