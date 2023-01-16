package org.example.testing.service;

import org.example.testing.repository.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityService<T extends AbstractEntity> {

    T save(T entity);

    List<T> search(String query, Pageable pageable);

}
