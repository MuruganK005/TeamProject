package com.TeamExampleProject.dao.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageDao {
    private int pageNumber;
    private int pageSize;
    private Sort.Direction direction;
    private String sortBy;
}
