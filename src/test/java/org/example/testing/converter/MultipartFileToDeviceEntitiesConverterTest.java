package org.example.testing.converter;

import org.example.testing.repository.entity.DeviceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class MultipartFileToDeviceEntitiesConverterTest {

    private Converter<MultipartFile, List<DeviceEntity>> converter;

    @BeforeEach
    void setUp() {
        converter = new MultipartFileToDeviceEntitiesConverter();
    }

    @Test
    public void checkSuccess() throws IOException {
        var data = Files.readAllBytes(Path.of(ResourceUtils.getFile("classpath:file.xml").getPath()));

        var file = new MockMultipartFile("name", "filename.xml", "", data);

        var result = converter.convert(file);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), "test@comp");
        assertEquals(result.get(0).getDpi(), 160);
        assertEquals(result.get(0).getWidth(), 1280);
        assertEquals(result.get(0).getHeight(), 752);
        assertEquals(result.get(0).getNewspaperName(), "abb");
    }

    @Test
    public void checkEmptyResultWhileException() throws IOException {

        var file = new MockMultipartFile("name", "filename.xml", "", new byte[] {});

        var result = converter.convert(file);

        assertEquals(result.size(), 0);
    }
}
