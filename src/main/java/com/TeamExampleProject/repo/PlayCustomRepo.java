package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Play;

import java.util.List;

public interface PlayCustomRepo  {

    List<Play> findByPlayName(String firstname);
}
