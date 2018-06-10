package com.excelsiorious.library.service;

import com.excelsiorious.library.entity.Language;
import com.excelsiorious.library.repository.BasicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private BasicRepository<Language> languageRepository;

    public LanguageService(BasicRepository<Language> languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Transactional
    public boolean create(Language entity) {
        return languageRepository.create(entity);
    }

    public Optional<Language> find(long id) {
        return languageRepository.find(id);
    }

    public Optional<Language> findByName(String name) {
        return languageRepository.findByName(name);
    }

    @Transactional
    public boolean update(Language entity) {
        return languageRepository.update(entity);
    }

    @Transactional
    public boolean delete(Language entity) {
        return languageRepository.delete(entity);
    }

    @Transactional
    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
