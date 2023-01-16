package org.example.testing.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.testing.validator.XmlSchemaValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = XmlSchemaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XmlSchema {

    String schemaName();

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
