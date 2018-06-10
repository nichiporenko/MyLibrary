package com.excelsiorious.library.service;

import com.excelsiorious.library.entity.Genre;
import com.excelsiorious.library.repository.BasicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private BasicRepository<Genre> genreRepository;

    public GenreService(BasicRepository<Genre> genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public boolean create(Genre entity) {
        return genreRepository.create(entity);
    }

    public Optional<Genre> find(long id) {
        return genreRepository.find(id);
    }

    public Optional<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Transactional
    public boolean update(Genre entity) {
        return genreRepository.update(entity);
    }

    @Transactional
    public boolean delete(Genre entity) {
        return genreRepository.delete(entity);
    }

    @Transactional
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
