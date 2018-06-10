package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepository implements BasicRepository<Country> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean create(Country entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<Country> find(long id) {
        return Optional.ofNullable(entityManager.find(Country.class, id));
    }

    @Override
    public Optional<Country> findByName(String... name) {
        List<Country> countries = entityManager.createNamedQuery("selectCountryByName", Country.class)
                .setParameter("name", name[0]).getResultList();
        if (countries.size() > 0) {
            return Optional.ofNullable(countries.get(0));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Country entity) {
        entityManager.joinTransaction();
        Optional<Country> optional = Optional.ofNullable(entityManager.find(Country.class, entity.getId()));
        if (optional.isPresent()) {
            optional.get().setName(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Country entity) {
        entityManager.joinTransaction();
        Optional<Country> optional = Optional.ofNullable(entityManager.find(Country.class, entity.getId()));
        if (optional.isPresent()) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    public List<Country> findAll() {
        return entityManager.createNamedQuery("selectAllCountries", Country.class).getResultList();
    }
}
