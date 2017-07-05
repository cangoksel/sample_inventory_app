package common.entity;

import java.util.Optional;

/**
 * Created by herdemir on 24/02/15.
 */
public interface EntityRepository {
    <T extends AbstractEntity> T save(T entity);

    <T extends AbstractEntity> Optional<T> find(Class<T> clazz, Object id);

    <T extends AbstractEntity> Optional<T> get(Class<T> clazz, Object id);

    <T extends AbstractEntity> T saveAndFlush(T dbk);

    <T extends AbstractEntity> void refresh(T entity);

    <T extends AbstractEntity> void delete(Class<T> clazz, Object id);

    <T extends AbstractEntity> void delete(T entity);

    <T extends AbstractEntity> void deleteAll(T... entities);
}
