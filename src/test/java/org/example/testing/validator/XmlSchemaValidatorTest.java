package org.example.testing.validator;

import jakarta.validation.ConstraintValidator;
import org.example.testing.validator.annotation.XmlSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class XmlSchemaValidatorTest {

    @Mock
    private XmlSchema annotation;

    private ConstraintValidator<XmlSchema, MultipartFile> validator;

    @BeforeEach
    void setUp() {
        validator = new XmlSchemaValidator();
    }

    @Test
    public void testValidatorWithoutSchemaName() throws IOException {
        assertFalse(validator.isValid(getMultipartFile(), null));
    }

    @Test
    public void testValidatorWithIncorrectSchemaName() throws IOException {
        when(annotation.schemaName()).thenReturn("incorrect");

        validator.initialize(annotation);

        assertFalse(validator.isValid(getMultipartFile(), null));
    }

    @Test
    public void testValidatorWithCorrectSchema() throws IOException {
        when(annotation.schemaName()).thenReturn("schema");

        validator.initialize(annotation);

        assertTrue(validator.isValid(getMultipartFile(), null));
    }

    @Test
    public void testValidatorWithIncorrectSchema() throws IOException {
        when(annotation.schemaName()).thenReturn("schema-incorrect");

        validator.initialize(annotation);

        assertTrue(validator.isValid(getMultipartFile(), null));
    }

    private MultipartFile getMultipartFile() throws IOException {
        var data = Files.readAllBytes(Path.of(ResourceUtils.getFile("classpath:file.xml").getPath()));

        return new MockMultipartFile("name", "filename.xml", "", data);
    }

}
