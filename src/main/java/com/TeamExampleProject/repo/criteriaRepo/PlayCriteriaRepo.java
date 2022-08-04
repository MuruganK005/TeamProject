package com.TeamExampleProject.repo.criteriaRepo;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.page.PageDao;
import com.TeamExampleProject.dao.searchCriteria.PlaySearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PlayCriteriaRepo {

    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public PlayCriteriaRepo (EntityManager entityManager){
        this.entityManager=entityManager;
        this.criteriaBuilder= entityManager.getCriteriaBuilder();
    }

    public Page<Play> findAllWithFilter(PageDao pageDao){
        CriteriaQuery<Play> criteriaQuery= criteriaBuilder.createQuery(Play.class);
        Root<Play> playRoot= criteriaQuery.from(Play.class);
        Predicate predicate=getPredicate(pageDao.getSearchCriteria().getPlayName(),playRoot);
        criteriaQuery.where(predicate);
//        setOrder(criteriaQuery,playRoot);

        TypedQuery<Play> typedQuery= entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageDao.getPageNumber()* pageDao.getPageSize());
        typedQuery.setMaxResults(pageDao.getPageSize());

        Pageable pageable=getPageable(pageDao);
        long playCount=getPlayCount(predicate);
        return  new PageImpl<>(typedQuery.getResultList(),pageable,playCount);
    }

    private Predicate getPredicate(String playSearchCriteria, Root<Play> playRoot) {
        List<Predicate> predicates=new ArrayList<>();
        if (Objects.nonNull(playSearchCriteria)){
            predicates.add(
                    criteriaBuilder.like(playRoot.get("playName"),"%"+playSearchCriteria+"%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(CriteriaQuery<Play> criteriaQuery, Root<Play> playRoot) {
        if ("ASC".equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(playRoot.get(Sort.DEFAULT_DIRECTION.name())));
        }else
        {
            criteriaQuery.orderBy(criteriaBuilder.desc(playRoot.get(Sort.DEFAULT_DIRECTION.name())));
        }
    }
    private Pageable getPageable(PageDao pageDao) {
//        Sort sort=Sort.by(pageDao.getDirection(),pageDao.getSortBy());
        return PageRequest.of(pageDao.getPageNumber(), pageDao.getPageSize(), Sort.Direction.ASC, "playName");
    }
    private long getPlayCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery= criteriaBuilder.createQuery(Long.class);
        Root<Play> countRoot= countQuery.from(Play.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
