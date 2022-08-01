package com.TeamExampleProject.controller;

import com.TeamExampleProject.dao.Tag;
import com.TeamExampleProject.dto.TagDto;
import com.TeamExampleProject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/createTag")
    public ResponseEntity<Object> createTags(@RequestBody TagDto dto){
        return tagService.createTags(dto);
    }
    @GetMapping("/getAllTags")
    public ResponseEntity<List<Tag>> getAllTags(){
        return tagService.gatAllTags();
    }
    @GetMapping("/getTagById/{id}")
    public ResponseEntity<Object> getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }
    @PutMapping("/updateTag/{id}")
    public ResponseEntity<Object> updateTag(@PathVariable Long id,@RequestBody TagDto dto){
        return tagService.updateTag(dto,id);
    }
    @DeleteMapping("/deleteTag/{id}")
    public ResponseEntity<String> deleteTagById(@PathVariable Long id){
        return tagService.deleteTagById(id);
    }
    @DeleteMapping("/deleteAllTag")
    public ResponseEntity<String> deleteAllTag(){
        return tagService.deleteAllTag();
    }
}
