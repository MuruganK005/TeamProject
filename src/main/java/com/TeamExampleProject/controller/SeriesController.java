package com.TeamExampleProject.controller;

import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.SeriesDto;
import com.TeamExampleProject.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SeriesController {
   @Autowired
   private SeriesService seriesService;

    @PostMapping("createSeries")
    public ResponseEntity<Object> createSeries(@RequestBody SeriesDto dto){
        return seriesService.createSeries(dto);
    }
    @GetMapping("/getAllSeries")
    public ResponseEntity<Object> getAllSeries(){
        return seriesService.getAllSeries();
    }
    @GetMapping("/getSeriesById/{id}")
    public ResponseEntity<Object> getSeriesById(@PathVariable Long id){
        return seriesService.getSeriesById(id);
    }
    @PutMapping("/updateSeries/{id}")
    public ResponseEntity<GenericDTO> updateSeries(@PathVariable Long id,@RequestBody SeriesDto dto){
        return seriesService.updateSeries(dto,id);
    }
    @DeleteMapping("/deleteBySeriesId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return seriesService.deleteById(id);
    }
    @DeleteMapping("/deleteAllSeries")
    public ResponseEntity<String> deleteAllSeries(){
        return seriesService.deleteAllSeries();
    }
}
