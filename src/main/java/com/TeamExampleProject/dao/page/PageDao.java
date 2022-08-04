package com.TeamExampleProject.dao.page;

import com.TeamExampleProject.dao.searchCriteria.PlaySearchCriteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDao {
    private int pageNumber;
    private int pageSize;
//    private String direction="ASC";
//    private String sortBy="DESC";
    private PlaySearchCriteria searchCriteria;
 }
