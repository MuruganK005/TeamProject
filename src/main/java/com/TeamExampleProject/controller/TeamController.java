package com.TeamExampleProject.controller;

import com.TeamExampleProject.dao.Team;
import com.TeamExampleProject.dto.TeamDto;
import com.TeamExampleProject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/createTeam")
    public ResponseEntity<Object> createTeam(@RequestBody TeamDto dto){
        return teamService.createTeam(dto);
    }
    @PutMapping("/updateTeam/{id}")
    public ResponseEntity<Object> updateTeam(@RequestBody TeamDto dto,@PathVariable Long id){
        return teamService.updateTeam(dto,id);
    }
    @GetMapping("/getAllTeam")
    public ResponseEntity<List<Team>> getAllTeam(){
        return teamService.getAllTeam();
    }
    @GetMapping("/getTeamById/{id}")
    public ResponseEntity<Object> getTeamById(@PathVariable Long id ){
        return teamService.getTeamById(id);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable Long id ){
        return teamService.deleteTeamById(id);
    }
    @DeleteMapping("/DeleteAllTeam")
    public ResponseEntity<String> deleteAllTeam(){
        return teamService.deleteAllTeam();
    }
}
