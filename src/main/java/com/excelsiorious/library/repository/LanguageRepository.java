package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.Language;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class LanguageRepository implements BasicRepository<Language> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean create(Language entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<Language> find(long id) {
        return Optional.ofNullable(entityManager.find(Language.class, id));
    }

    @Override
    public Optional<Language> findByName(String... name) {
        List<Language> languages = entityManager.createNamedQuery("selectLanguageByName", Language.class)
                .setParameter("name", name[0]).getResultList();
        if (languages.size() > 0) {
            return Optional.ofNullable(languages.get(0));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Language entity) {
        Optional<Language> optional = Optional.ofNullable(entityManager.find(Language.class, entity.getId()));
        if (optional.isPresent()) {
            optional.get().setName(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Language entity) {
        Optional<Language> optional = Optional.ofNullable(entityManager.find(Language.class, entity.getId()));
        if (optional.isPresent()) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Language> findAll() {
        return entityManager.createNamedQuery("selectAllLanguages", Language.class).getResultList();
    }
}
