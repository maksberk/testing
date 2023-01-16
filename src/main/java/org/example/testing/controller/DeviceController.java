package org.example.testing.controller;

import lombok.AllArgsConstructor;
import org.example.testing.dto.DeviceDto;
import org.example.testing.repository.entity.DeviceEntity;
import org.example.testing.service.EntityService;
import org.example.testing.validator.annotation.XmlSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceController {

    @Autowired
    private final EntityService<DeviceEntity> deviceService;

    @Autowired
    private final Converter<DeviceEntity, DeviceDto> deviceEntityToDeviceDtoConverter;

    @Autowired
    private final Converter<MultipartFile, List<DeviceEntity>> multipartFileToDeviceEntitiesConverter;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("file") @XmlSchema(schemaName = "device") MultipartFile file) {
        var devices = multipartFileToDeviceEntitiesConverter.convert(file);

        Objects.requireNonNull(devices).forEach(device -> {
            device.setFileName(file.getOriginalFilename());
            deviceService.save(device);
        });
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> search(@RequestParam("query") String query, Pageable pageable) {
        return deviceService.search(query, pageable).stream().map(deviceEntityToDeviceDtoConverter::convert).collect(Collectors.toList());
    }

}
