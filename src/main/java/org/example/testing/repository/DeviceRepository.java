package org.example.testing.repository;

import org.example.testing.repository.entity.DeviceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends EntityRepository<DeviceEntity> {
}
