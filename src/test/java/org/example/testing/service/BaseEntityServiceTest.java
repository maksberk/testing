package org.example.testing.service;

import org.example.testing.repository.EntityRepository;
import org.example.testing.repository.entity.DeviceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BaseEntityServiceTest {

    private BaseEntityService<DeviceEntity> service;

    @Mock
    private EntityRepository<DeviceEntity> repository;

    @BeforeEach
    void setUp() {
        service = new BaseEntityService<>(repository);
    }

    @Test
    public void checkSearch() {
        var query = "query";
        var pageable = PageRequest.of(0 , 10);
        var expectedResult = List.of(new DeviceEntity());

        when(repository.search(query, pageable)).thenReturn(expectedResult);

        assertEquals(service.search(query, pageable), expectedResult);
    }

    @Test
    public void checkSaveWithId() {
        var entity = new DeviceEntity();
        entity.setId(UUID.randomUUID().toString());

        when(repository.save(entity)).thenReturn(entity);

        assertEquals(service.save(entity), entity);
    }

    @Test
    public void checkSaveWithoutId() {
        assertNull(service.save(new DeviceEntity()));
    }
}
