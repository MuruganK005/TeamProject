package com.TeamExampleProject.service.serviceImpl;

import com.TeamExampleProject.dao.Team;
import com.TeamExampleProject.dto.TeamDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamServiceImpl {
    ResponseEntity<Object> createTeam(TeamDto dto);

    ResponseEntity<Object> updateTeam(TeamDto dto, Long id);

    ResponseEntity<List<Team>> getAllTeam();

    ResponseEntity<Object> getTeamById(Long id);

    ResponseEntity<String> deleteTeamById(Long id);

    ResponseEntity<String> deleteAllTeam();
}
