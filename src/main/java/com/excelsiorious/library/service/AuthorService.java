package com.excelsiorious.library.service;

import com.excelsiorious.library.entity.Author;
import com.excelsiorious.library.repository.BasicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private BasicRepository<Author> authorRepository;

    public AuthorService(BasicRepository<Author> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public boolean create(Author entity) {
        return authorRepository.create(entity);
    }

    public Optional<Author> find(long id) {
        return authorRepository.find(id);
    }

    public Optional<Author> findByName(String firstName, String lastName) {
        return authorRepository.findByName(firstName, lastName);
    }

    @Transactional
    public boolean update(Author entity) {
        return authorRepository.update(entity);
    }

    @Transactional
    public boolean delete(Author entity) {
        return authorRepository.delete(entity);
    }

    @Transactional
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
