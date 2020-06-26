package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.databind.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = NewBookBuilder.ImmutableNewBook.class)
@JsonDeserialize(as = NewBookBuilder.ImmutableNewBook.class)
@Schema(description = "New book instance")
public interface NewBook extends AbstractBook
{
    interface Builder extends AbstractBook.Builder<Builder>
    {
        NewBook build();
    }

    static Builder builder()
    {
        return new NewBookBuilder();
    }
}