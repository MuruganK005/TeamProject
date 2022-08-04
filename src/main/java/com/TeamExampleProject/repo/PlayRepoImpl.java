package com.TeamExampleProject.repo;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.searchCriteria.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public class PlayRepoImpl implements PlayCustomRepo{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Play> findByPlayName(String playName) {

        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Play> cq=cb.createQuery(Play.class);

        Root<Play> playRoot=cq.from(Play.class);
        cq.select(playRoot);

        Predicate firstPlayPredicate=cb.like(playRoot.get("playName"),"%"+playName+"%");
        Order firstPlayPredicate1=cb.asc(playRoot.get(playName));

        cq.where(firstPlayPredicate, (Predicate) firstPlayPredicate1);
        cq.orderBy(cb.desc(playRoot.get("playName")));

        TypedQuery<Play> query=entityManager.createQuery(cq);

        return query.getResultList();
    }
    @Override
    public Page<Play> getFilterPlay(Play params,
                                       Pageable pageable) {

        CriteriaBuilder builder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Play> criteria = builder.createQuery(Play.class);
        Root<Play> playRoot = criteria.from(Play.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        predicates.add(builder.equal(playRoot.get("id"), params.getId()));

        predicates.add(builder.like(builder.lower(playRoot.get("name")),
                "%" + params.getPlayName().toLowerCase() + "%"));

        criteria.where(builder.and(predicates.toArray( new Predicate[predicates.size()])));

        criteria.orderBy(builder.desc(playRoot.get("id")));

        // This query fetches the Plays as per the Page Limit
        List<Play> result = (List<Play>) entityManager.createQuery(criteria).setFirstResult(pageable.getNumberOfPages()).setMaxResults(pageable.getNumberOfPages());

        // Create Count Query
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Play> booksRootCount = countQuery.from(Play.class);
        countQuery.select(builder.count(booksRootCount)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        // Fetches the count of all Play as per given criteria
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        Page<Play> result1 = new PageImpl<>(result, (org.springframework.data.domain.Pageable) pageable, count);
        return result1;
    }


}
