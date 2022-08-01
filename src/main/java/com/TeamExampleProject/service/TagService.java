package com.TeamExampleProject.service;

import com.TeamExampleProject.dao.Tag;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.TagDto;
import com.TeamExampleProject.repo.TagRepo;
import com.TeamExampleProject.service.serviceImpl.TagServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements TagServiceImpl {
    @Autowired
    private TagRepo tagRepo;
    @Override
    public ResponseEntity<Object> createTags(TagDto dto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Tag tag=mapper.map(dto,Tag.class);
        tagRepo.save(tag);
        GenericDTO dto1=new GenericDTO();
        dto1.setObject(tag);
        dto1.setMessage("Tag has Created Succesfully");
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Tag>> gatAllTags() {
        List<Tag> tags=tagRepo.findAll();
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getTagById(Long id) {
        Optional<Tag> tag=tagRepo.findById(id);
        return new ResponseEntity<>(tag,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateTag(TagDto dto, Long id) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Tag tag=mapper.map(dto,Tag.class);
        tagRepo.save(tag);
        GenericDTO dto1=new GenericDTO();
        dto1.setObject(tag);
        dto1.setMessage("Tag id "+id+" has Been updated Successfully ");
        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteTagById(Long id) {
        tagRepo.deleteById(id);
        return new ResponseEntity<String>("Tag id "+id+" has been successfully deleted",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAllTag() {
        tagRepo.deleteAll();
        return new ResponseEntity<String>("All Tags Are Deleted Successfully",HttpStatus.OK);
    }
}
