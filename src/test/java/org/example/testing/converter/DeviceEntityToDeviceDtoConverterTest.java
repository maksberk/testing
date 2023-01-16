package org.example.testing.converter;

import org.example.testing.dto.DeviceDto;
import org.example.testing.repository.entity.DeviceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class DeviceEntityToDeviceDtoConverterTest {

    private Converter<DeviceEntity, DeviceDto> converter;

    @BeforeEach
    void setUp() {
        converter = new DeviceEntityToDeviceDtoConverter();
    }

    @Test
    public void checkConvertWithNullEntity() {
        assertNull(converter.convert(null));
    }

    @Test
    public void checkConvertSuccess() {
        var entity = new DeviceEntity();

        entity.setId(UUID.randomUUID().toString());
        entity.setDpi(1);
        entity.setWidth(2);
        entity.setHeight(3);
        entity.setFileName(UUID.randomUUID().toString());
        entity.setNewspaperName(UUID.randomUUID().toString());
        entity.setCreateDate(LocalDateTime.now());

        var dto = converter.convert(entity);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getDpi(), entity.getDpi());
        assertEquals(dto.getWidth(), entity.getWidth());
        assertEquals(dto.getHeight(), entity.getHeight());
        assertEquals(dto.getFileName(), entity.getFileName());
        assertEquals(dto.getNewspaperName(), entity.getNewspaperName());
        assertEquals(dto.getUploadTime(), entity.getCreateDate());
    }
}
