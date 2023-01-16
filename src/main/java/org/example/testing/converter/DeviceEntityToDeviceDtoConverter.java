package org.example.testing.converter;

import org.example.testing.dto.DeviceDto;
import org.example.testing.repository.entity.DeviceEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceEntityToDeviceDtoConverter implements Converter<DeviceEntity, DeviceDto>  {

    @Override
    public DeviceDto convert(DeviceEntity source) {
        if (source == null) {
            return null;
        }

        var result = new DeviceDto();

        result.setId(source.getId());
        result.setDpi(source.getDpi());
        result.setWidth(source.getWidth());
        result.setHeight(source.getHeight());
        result.setFileName(source.getFileName());
        result.setNewspaperName(source.getNewspaperName());
        result.setUploadTime(source.getCreateDate());

        return result;
    }
}
