package org.example.testing.service;

import lombok.AllArgsConstructor;
import org.example.testing.repository.EntityRepository;
import org.example.testing.repository.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
public class BaseEntityService<T extends AbstractEntity> implements EntityService<T> {

    @Autowired
    private final EntityRepository<T> repository;

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            return null;
        }

        return repository.save(entity);
    }

    @Override
    public List<T> search(String query, Pageable pageable) {
        return repository.search(query, pageable);
    }

}
