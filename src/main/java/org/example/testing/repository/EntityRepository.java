package org.example.testing.repository;

import io.github.perplexhub.rsql.RSQLJPASupport;
import org.example.testing.repository.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityRepository<T extends AbstractEntity> extends CrudRepository<T, String>, JpaSpecificationExecutor<T> {

    default List<T> search(String query, Pageable pageable) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        if (pageable == null) {
            return findAll(RSQLJPASupport.toSpecification(query));
        }

        return findAll(RSQLJPASupport.toSpecification(query), pageable).getContent();
    }

}
