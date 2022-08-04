package com.TeamExampleProject.service.page;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.page.PageDao;
import com.TeamExampleProject.dao.searchCriteria.Document;
import com.TeamExampleProject.dao.searchCriteria.PlaySearchCriteria;
import com.TeamExampleProject.repo.PlayRepo;
import com.TeamExampleProject.repo.criteriaRepo.PlayCriteriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    private PlayRepo playRepo;
    @Autowired
    private PlayCriteriaRepo playCriteriaRepo;


    public Page<Play> getPlays(PageDao pageDao){
        return playCriteriaRepo.findAllWithFilter(pageDao);
    }


}
