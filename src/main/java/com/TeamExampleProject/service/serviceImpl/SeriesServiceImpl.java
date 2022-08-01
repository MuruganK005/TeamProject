package com.TeamExampleProject.service.serviceImpl;

import com.TeamExampleProject.dao.Series;
import com.TeamExampleProject.dto.GenericDTO;
import com.TeamExampleProject.dto.SeriesDto;
import org.springframework.http.ResponseEntity;

public interface SeriesServiceImpl {
    ResponseEntity<Object> createSeries(SeriesDto dto);

    ResponseEntity<Object> getAllSeries();

    ResponseEntity<Object> getSeriesById(Long id);

    ResponseEntity<GenericDTO> updateSeries(SeriesDto dto, Long id);

    ResponseEntity<String> deleteById(Long id);

    ResponseEntity<String> deleteAllSeries();
}
