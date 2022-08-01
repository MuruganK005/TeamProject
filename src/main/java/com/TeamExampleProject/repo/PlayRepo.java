package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayRepo extends JpaRepository<Play,Long>,PlayCustomRepo, JpaSpecificationExecutor<Play> {
    List<Play> findByPlayName(String firstname);

}
