package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository implements BasicRepository<Genre> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean create(Genre entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<Genre> find(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> findByName(String... name) {
        List<Genre> genres = entityManager.createNamedQuery("selectGenreByName", Genre.class)
                .setParameter("name", name[0]).getResultList();
        if (genres.size() > 0) {
            return Optional.ofNullable(genres.get(0));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Genre entity) {
        Optional<Genre> optional = Optional.ofNullable(entityManager.find(Genre.class, entity.getId()));
        if (optional.isPresent()) {
            optional.get().setName(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Genre entity) {
        Optional<Genre> optional = Optional.ofNullable(entityManager.find(Genre.class, entity.getId()));
        if (optional.isPresent()) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Genre> findAll() {
        return entityManager.createNamedQuery("selectAllGenres", Genre.class).getResultList();
    }
}
