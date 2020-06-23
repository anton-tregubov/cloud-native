package ru.faulab.cloud.bookstore.application.api;


import org.immutables.value.Value;

import java.lang.annotation.*;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
        get = {"is*", "get*"},
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        builderVisibility = Value.Style.BuilderVisibility.PACKAGE,
        addAll = "add*",
        optionalAcceptNullable = true,
        defaults = @Value.Immutable(copy = false),
        implementationNestedInBuilder = true
)
public @interface DomainEntity
{
}
