package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.searchCriteria.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PlayRepo extends JpaRepository<Play,Long>,PlayCustomRepo, JpaSpecificationExecutor<Play>{
    List<Play> findByPlayName(String PlayName);
    Page<Play> getFilterPlay(Play params, Pageable pageable);
}
