package com.TeamExampleProject.controller;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.PlayDto;
import com.TeamExampleProject.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PlayController {
    @Autowired
   private PlayService playService;
    @PostMapping("createPlay")
    public ResponseEntity<String> createPlay(@RequestBody PlayDto playDto) {
        return playService.createPlay(playDto);
    }
    @GetMapping("/AllPlay")
    public ResponseEntity<List<Play>> getAllTeam(){
        return playService.getAllPlay();
    }
    @PostMapping("createPlayByResponseEntity")
    public ResponseEntity<GenericDTO> createPlayByResponseEntity(@RequestBody PlayDto playDto){
        return playService.createPlayByResponseEntity(playDto);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<Object> getByIdPlay(@PathVariable Long id){
        return playService.getByIdPlay(id);
    }
    @PutMapping("UpdatePlay/{id}")
    public ResponseEntity<Object> updatePlayById(@PathVariable Long id,@RequestBody PlayDto playDto){
            return playService.updatePlayById(id,playDto);
    }
    @DeleteMapping("deleteByPlayId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ){
        return playService.deletePlayById(id);
    }
    @DeleteMapping("/deleteAllPlay")
    public ResponseEntity<String> deleteAllPlay(){
        return playService.deleteAllPlay();
    }
//    @GetMapping("getByPlayName")
//    public ResponseEntity<String> getPlayName(@RequestParam String playName)
//    {
//        return playService.getByPlayName(playName);
//    }
}
