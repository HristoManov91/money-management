package com.example.money_management_be.validation;

import com.example.money_management_be.validation.UniqueField.UniqueFields;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates the field for a unique value in database.If you need to validate more fields for each field you need a new annotation <br/>
 * <p/>
 * - "entityClass" is required param which needed on EntityManger to know which class to validate <br/>
 * - "dtoFieldName" required param which field to check <br/>
 * - "message" this parameter does not need to be provided, it is loaded automatically from message.properties in common service
 */
@Repeatable(UniqueFields.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = UniqueFieldValidator.class)
public @interface UniqueField {

    Class<?> entityClass();

    String dtoFieldName();

    String message() default "Value already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface UniqueFields {

        UniqueField[] value();
    }
}
