package com.excelsiorious.library.service;

import com.excelsiorious.library.entity.Book;
import com.excelsiorious.library.repository.BasicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BasicRepository<Book> bookRepository;

    public BookService(BasicRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public boolean create(Book entity) {
        return bookRepository.create(entity);
    }

    public Optional<Book> find(long id) {
        return bookRepository.find(id);
    }

    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional
    public boolean update(Book entity) {
        return bookRepository.update(entity);
    }

    @Transactional
    public boolean delete(Book entity) {
        return bookRepository.delete(entity);
    }

    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
