package org.example.testing.controller;

import org.example.testing.dto.DeviceDto;
import org.example.testing.repository.entity.DeviceEntity;
import org.example.testing.service.EntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DeviceControllerTest {

    private DeviceController controller;

    @Mock
    private EntityService<DeviceEntity> deviceService;

    @Mock
    private Converter<DeviceEntity, DeviceDto> deviceEntityToDeviceDtoConverter;

    @Mock
    private Converter<MultipartFile, List<DeviceEntity>> multipartFileToDeviceEntitiesConverter;

    @BeforeEach
    void setUp() {
        controller = new DeviceController(deviceService, deviceEntityToDeviceDtoConverter, multipartFileToDeviceEntitiesConverter);
    }

    @Test
    public void checkUploadWithDto() {
        var dtoList = List.of(new DeviceEntity());
        var file = new MockMultipartFile("test", "test.xml", "test", new byte[]{});

        when(multipartFileToDeviceEntitiesConverter.convert(file)).thenReturn(dtoList);

        controller.upload(file);

        verify(deviceService).save(dtoList.get(0));
        assertEquals(dtoList.get(0).getFileName(), file.getOriginalFilename());
    }

    @Test
    public void checkUploadWithoutDto() {
        var file = new MockMultipartFile("test", "test.xml", "test", new byte[]{});

        when(multipartFileToDeviceEntitiesConverter.convert(file)).thenReturn(List.of());

        controller.upload(file);

        verify(deviceService, times(0)).save(any());
    }


    @Test
    public void checkSearch() {
        var query = "query";
        var pageable = PageRequest.of(0, 10);

        var expectedResult = new DeviceDto();

        when(deviceService.search(query, pageable)).thenReturn(List.of(new DeviceEntity()));
        when(deviceEntityToDeviceDtoConverter.convert(any(DeviceEntity.class))).thenReturn(expectedResult);

        var result = controller.search(query, pageable);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0), expectedResult);
    }

}
