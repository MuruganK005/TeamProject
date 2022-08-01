package com.TeamExampleProject.dto;

import com.TeamExampleProject.dao.Series;
import com.TeamExampleProject.dao.Tag;
import com.TeamExampleProject.dao.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PlayDto {
    private Long id;
    private String playName;
    private Series series;
    private Team team;
    private List<Tag> tags=new ArrayList<>();
}
