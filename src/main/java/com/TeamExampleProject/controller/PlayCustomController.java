package com.TeamExampleProject.controller;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.repo.PlayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.TeamExampleProject.Specification.PlaySpecification.hasPlayName;

@RestController
@RequestMapping("/api/v1")
public class PlayCustomController {
    @Autowired
    private PlayRepo customRepo;
    @GetMapping("/playName/{playName}")
    public List<Play> findByPlayName(@PathVariable("playName") String playName){
        return customRepo.findAll((hasPlayName(playName)));
    }
}
