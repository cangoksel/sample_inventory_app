package com.github.cangoksel.sample.common;

import com.github.cangoksel.common.entity.AbstractEntity;
import com.github.cangoksel.common.entity.EntityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * Created by herdemir on 24/02/15.
 */
@Repository
public class EntityRepositoryImpl implements EntityRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public <T extends AbstractEntity> T save(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <T extends AbstractEntity> Optional<T> find(Class<T> clazz, Object id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public <T extends AbstractEntity> Optional<T> get(Class<T> clazz, Object id) {
        try {
            return Optional.ofNullable(entityManager.getReference(clazz, id));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public <T extends AbstractEntity> T saveAndFlush(T entity) {
        entity = entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public <T extends AbstractEntity> void refresh(T entity) {
        entityManager.refresh(entity);
    }

    @Override
    public <T extends AbstractEntity> void delete(Class<T> clazz, Object id) {
        final Optional<T> entity = find(clazz, id);
        entity.ifPresent(this::delete);
    }

    @Override
    public <T extends AbstractEntity> void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public <T extends AbstractEntity> void deleteAll(T... entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }
}
