package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Play;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class PlayRepoImpl implements PlayCustomRepo{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Play> findByPlayName(String playName) {

        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery(Play.class);

        Root<Play> playRoot=cq.from(Play.class);

        Predicate firstPlayPredicate=cb.equal(playRoot.get("playName"),playName);

        cq.where(firstPlayPredicate);

        TypedQuery<Play> query=entityManager.createQuery(cq);

        return query.getResultList();
    }
}
