package com.TeamExampleProject.service.serviceImpl;

import com.TeamExampleProject.dao.Tag;
import com.TeamExampleProject.dto.TagDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagServiceImpl {
    ResponseEntity<Object> createTags(TagDto dto);

    ResponseEntity<List<Tag>> gatAllTags();

    ResponseEntity<Object> getTagById(Long id);

    ResponseEntity<Object> updateTag(TagDto dto, Long id);

    ResponseEntity<String> deleteTagById(Long id);

    ResponseEntity<String> deleteAllTag();
}
