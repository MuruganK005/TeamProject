package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepo extends JpaRepository<Series,Long> {
}
