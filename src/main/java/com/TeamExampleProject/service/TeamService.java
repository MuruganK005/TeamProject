package com.TeamExampleProject.service;

import com.TeamExampleProject.dao.Team;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.TeamDto;
import com.TeamExampleProject.repo.TeamRepo;
import com.TeamExampleProject.service.serviceImpl.TeamServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService implements TeamServiceImpl {
    @Autowired
    private TeamRepo teamRepo;
    @Override
    public ResponseEntity<Object> createTeam(TeamDto dto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Team team=mapper.map(dto, Team.class);
        teamRepo.save(team);
        GenericDTO dto1=new GenericDTO();
        dto1.setObject(team);
        dto1.setMessage("Team has been Created Successfully");
        return new ResponseEntity<Object>(dto1, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateTeam(TeamDto dto, Long id) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Team team=mapper.map(dto,Team.class);
        teamRepo.save(team);
        GenericDTO dto1=new GenericDTO();
        dto1.setObject(team);
        dto1.setMessage("Team id "+id+ " Updated Successfully ");
        return new ResponseEntity<Object>(dto1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Team>> getAllTeam() {
        List<Team> teams=teamRepo.findAll();
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getTeamById(Long id) {
        Optional<Team> team=teamRepo.findById(id);
        return new ResponseEntity<>(team,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteTeamById(Long id) {
        teamRepo.deleteById(id);
        return new ResponseEntity<String>("Team id "+id+ " has been deleted Successfully ",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAllTeam() {
        teamRepo.deleteAll();
        return new ResponseEntity<>("Teams All Deleted Successfully ",HttpStatus.OK);
    }
}
