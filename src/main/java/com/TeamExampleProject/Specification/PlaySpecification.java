package com.TeamExampleProject.Specification;

import com.TeamExampleProject.dao.Play;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PlaySpecification {
    public static Specification<Play> hasPlayName(String playName){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("playName"),playName));
    }
}
