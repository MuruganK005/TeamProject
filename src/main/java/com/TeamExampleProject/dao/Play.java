package com.TeamExampleProject.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity(name = "play")
@AllArgsConstructor
@NoArgsConstructor
public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "play_name")
    private String playName;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Series series;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Team team;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();
    private Date createdAt = new Date();
    private Date UpdatedAt = new Date();
}