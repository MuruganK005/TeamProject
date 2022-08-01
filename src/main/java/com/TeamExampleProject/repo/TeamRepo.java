package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {
}
