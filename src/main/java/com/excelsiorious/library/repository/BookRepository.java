package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements BasicRepository<Book> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean create(Book entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<Book> find(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Optional<Book> findByName(String... name) {
        List<Book> books = entityManager.createNamedQuery("selectBookByName", Book.class)
                .setParameter("name", name[0]).getResultList();
        if (books.size() > 0) {
            return Optional.ofNullable(books.get(0));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Book entity) {
        Optional<Book> optional = Optional.ofNullable(entityManager.find(Book.class, entity.getId()));
        if (optional.isPresent()) {
            Book bookFound = optional.get();
            bookFound.setName(entity.getName());
            bookFound.setAuthor(entity.getAuthor());
            bookFound.setCountry(entity.getCountry());
            bookFound.setGenre(entity.getGenre());
            bookFound.setLanguage(entity.getLanguage());
            bookFound.setPublishingHouse(entity.getPublishingHouse());
            bookFound.setCirculation(entity.getCirculation());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Book entity) {
        Optional<Book> optional = Optional.ofNullable(entityManager.find(Book.class, entity.getId()));
        if (optional.isPresent()) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Book> findAll() {
        return entityManager.createNamedQuery("selectAllBooks", Book.class).getResultList();
    }
}
