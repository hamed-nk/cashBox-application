package com.demisco.cashbox.serviceimpl;

import com.demisco.cashbox.service.CodeEnabled;
import com.demisco.cashbox.service.DoaService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class DoaServiceImpl implements DoaService {
    private final EntityManager entityManager;

    public DoaServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T extends CodeEnabled> Optional<T> findByCode(Class<T> tClass, String code) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        CriteriaQuery<T> select = criteriaQuery.select(root);

        Predicate p = criteriaBuilder.equal(root.get("code"), code);

        CriteriaQuery<T> where = select.where(p);
        TypedQuery<T> query = entityManager.createQuery(where);

        List<T> resultList = query.getResultList();
        return Optional.of(resultList.get(0));
    }
}
