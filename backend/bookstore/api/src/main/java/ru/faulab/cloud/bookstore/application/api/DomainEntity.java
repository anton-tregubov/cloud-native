package ru.faulab.cloud.bookstore.application.api;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.lang.annotation.*;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@JsonSerialize // Jackson automatic integration, why not?
@Value.Style(
        get = {"is*", "get*"},
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        builderVisibility = Value.Style.BuilderVisibility.PACKAGE,
        addAll = "add*",
        optionalAcceptNullable = true,
        defaults = @Value.Immutable(copy = false),
        implementationNestedInBuilder = true,
        validationMethod = Value.Style.ValidationMethod.VALIDATION_API

)
public @interface DomainEntity
{
}
