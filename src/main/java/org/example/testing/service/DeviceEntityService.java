package org.example.testing.service;

import org.example.testing.repository.EntityRepository;
import org.example.testing.repository.entity.DeviceEntity;
import org.springframework.stereotype.Service;

@Service
public class DeviceEntityService extends BaseEntityService<DeviceEntity> {

    public DeviceEntityService(EntityRepository<DeviceEntity> repository) {
        super(repository);
    }

}
