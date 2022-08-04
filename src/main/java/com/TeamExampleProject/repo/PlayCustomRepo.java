package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.searchCriteria.Document;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface PlayCustomRepo  {

    List<Play> findByPlayName(String playName);
    Page<Play> getFilterPlay(Play params, Pageable pageable);


}
