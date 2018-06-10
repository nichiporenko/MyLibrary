package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BasicRepository<Author> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean create(Author entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<Author> find(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Optional<Author> findByName(String... name) {
        List<Author> authors = entityManager.createNamedQuery("selectAuthorByName", Author.class)
                .setParameter("firstName", name[0])
                .setParameter("lastName", name[1]).getResultList();
        if (authors.size() > 0) {
            return Optional.ofNullable(authors.get(0));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Author entity) {
        Optional<Author> optional = Optional.ofNullable(entityManager.find(Author.class, entity.getId()));
        if (optional.isPresent()) {
            Author authorFound = optional.get();
            authorFound.setFirstName(entity.getFirstName());
            authorFound.setLastName(entity.getLastName());
            authorFound.setDateOfBirth(entity.getDateOfBirth());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Author entity) {
        Optional<Author> optional = Optional.ofNullable(entityManager.find(Author.class, entity.getId()));
        if (optional.isPresent()) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Author> findAll() {
        return entityManager.createNamedQuery("selectAllAuthors", Author.class).getResultList();
    }
}
