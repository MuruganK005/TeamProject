package com.TeamExampleProject.service;

import com.TeamExampleProject.dao.Series;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.SeriesDto;
import com.TeamExampleProject.repo.SeriesRepo;
import com.TeamExampleProject.service.serviceImpl.SeriesServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesService implements SeriesServiceImpl {
    @Autowired
    private SeriesRepo seriesRepo;
    @Override
    public ResponseEntity<Object> createSeries(SeriesDto dto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Series series=mapper.map(dto,Series.class);
        seriesRepo.save(series);
        GenericDTO dto1=new GenericDTO();
        dto1.setObject(series);
        dto1.setMessage("Series Has created Succesfully");
        return new ResponseEntity<Object>(dto1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllSeries() {
       List<Series> series= seriesRepo.findAll();
        return new ResponseEntity<Object>(series,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getSeriesById(Long id) {
         Optional<Series> seriesId=seriesRepo.findById(id);
        return new ResponseEntity<Object>(seriesId,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GenericDTO> updateSeries(SeriesDto dto, Long id) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Series series=mapper.map(dto,Series.class);
        GenericDTO dto1=new GenericDTO();
        dto1.setMessage("Series id "+id+" has updated SuccessFully");
        dto1.setObject(series);
        return new ResponseEntity<GenericDTO>(dto1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        seriesRepo.deleteById(id);
        return new ResponseEntity<String>("Series "+id+" id deleted Succesfully",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAllSeries() {
        seriesRepo.deleteAll();
        return new ResponseEntity<>("Series all Deleted Succesfully",HttpStatus.OK);
    }
}
