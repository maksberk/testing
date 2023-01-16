package org.example.testing.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.testing.validator.annotation.XmlSchema;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;

public class XmlSchemaValidator implements ConstraintValidator<XmlSchema, MultipartFile> {

    private String schemaName;

    @Override
    public void initialize(XmlSchema constraintAnnotation) {
        schemaName = constraintAnnotation.schemaName();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = factory.newSchema(ResourceUtils.getFile("classpath:schema/" + schemaName + ".xsd"));

            schema.newValidator().validate(new StreamSource(multipartFile.getInputStream()));
        } catch (SAXException | IOException e) {
            return false;
        }

        return true;
    }
}
