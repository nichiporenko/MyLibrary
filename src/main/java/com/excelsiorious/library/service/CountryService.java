package com.excelsiorious.library.service;

import com.excelsiorious.library.entity.Country;
import com.excelsiorious.library.repository.BasicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private BasicRepository<Country> countryRepository;

    public CountryService(BasicRepository<Country> countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    public boolean create(Country entity) {
        return countryRepository.create(entity);
    }

    public Optional<Country> find(long id) {
        return countryRepository.find(id);
    }

    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Transactional
    public boolean update(Country entity) {
        return countryRepository.update(entity);
    }

    @Transactional
    public boolean delete(Country entity) {
        return countryRepository.delete(entity);
    }

    @Transactional
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
