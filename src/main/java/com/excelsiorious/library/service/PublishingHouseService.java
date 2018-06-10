package com.excelsiorious.library.service;

import com.excelsiorious.library.entity.PublishingHouse;
import com.excelsiorious.library.repository.BasicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingHouseService {
    private BasicRepository<PublishingHouse> publishingHouseRepository;

    public PublishingHouseService(BasicRepository<PublishingHouse> publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @Transactional
    public boolean create(PublishingHouse entity) {
        return publishingHouseRepository.create(entity);
    }

    public Optional<PublishingHouse> find(long id) {
        return publishingHouseRepository.find(id);
    }

    public Optional<PublishingHouse> findByName(String name) {
        return publishingHouseRepository.findByName(name);
    }

    @Transactional
    public boolean update(PublishingHouse entity) {
        return publishingHouseRepository.update(entity);
    }

    @Transactional
    public boolean delete(PublishingHouse entity) {
        return publishingHouseRepository.delete(entity);
    }

    @Transactional
    public List<PublishingHouse> findAll() {
        return publishingHouseRepository.findAll();
    }
}
