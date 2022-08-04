package com.TeamExampleProject.service.serviceImpl;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.searchCriteria.Document;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.PlayDto;
import org.hibernate.mapping.Any;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayServiceImpl {
    ResponseEntity<String> createPlay(PlayDto playDto);

    ResponseEntity<List<Play>> getAllPlay();

    ResponseEntity<GenericDTO> createPlayByResponseEntity(PlayDto playDto);

    ResponseEntity<Object> getByIdPlay(Long id);

    ResponseEntity<Object> updatePlayById(Long id, PlayDto playDto);

    ResponseEntity<String> deletePlayById(Long id);

    ResponseEntity<String> deleteAllPlay();



}
