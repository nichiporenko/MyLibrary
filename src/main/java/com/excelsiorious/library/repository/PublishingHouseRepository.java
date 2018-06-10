package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.PublishingHouse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PublishingHouseRepository implements BasicRepository<PublishingHouse> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean create(PublishingHouse entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<PublishingHouse> find(long id) {
        return Optional.ofNullable(entityManager.find(PublishingHouse.class, id));
    }

    @Override
    public Optional<PublishingHouse> findByName(String... name) {
        List<PublishingHouse> publishingHouses = entityManager.createNamedQuery("selectPublishingHouseByName", PublishingHouse.class)
                .setParameter("name", name[0]).getResultList();
        if (publishingHouses.size() > 0) {
            return Optional.ofNullable(publishingHouses.get(0));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(PublishingHouse entity) {
        Optional<PublishingHouse> optional = Optional.ofNullable(entityManager.find(PublishingHouse.class, entity.getId()));
        if (optional.isPresent()) {
            optional.get().setName(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(PublishingHouse entity) {
        Optional<PublishingHouse> optional = Optional.ofNullable(entityManager.find(PublishingHouse.class, entity.getId()));
        if (optional.isPresent()) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PublishingHouse> findAll() {
        return entityManager.createNamedQuery("selectAllPublishingHouses", PublishingHouse.class).getResultList();
    }
}
