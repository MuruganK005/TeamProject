package com.TeamExampleProject.dao.searchCriteria;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String docName;

    @Column
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] file;

}