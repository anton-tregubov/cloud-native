package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.databind.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.immutables.value.Value;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = BookHistoryItemBuilder.ImmutableBookHistoryItem.class)
@JsonDeserialize(as = BookHistoryItemBuilder.ImmutableBookHistoryItem.class)
@Schema(description = "Record about book state change")
public interface BookHistoryItem extends Serializable
{
    @NotNull
    @Schema(description = "Book that have been change state", example = "{'todo':'yes'}")
    Book getBook();

    @NotNull
    @PastOrPresent
    @Schema(description = "When book change state", example = "todo")
    Instant getWhen();

    @NotNull
    @Schema(description = "New book state", example = "ENTER")
    BookState getState();

    @NotNull
    @Schema(description = "Bookstore operator that make change", example = "{'todo':'yes'}")
    Human getInternalActor();

    @NotNull
    @Schema(description = "Organization that interact with bookstore operator", example = "{'todo':'yes'}")
    Organization getExternalActor();

    @Schema(description = "Any token to track external reference operation", example = "fg34gww4rfw")
    Optional<String> getExternalOperationId();

    interface Builder
    {
        Builder book(Book book);

        Builder when(Instant when);

        Builder state(BookState state);

        Builder internalActor(Human internalActor);

        Builder externalActor(Organization externalActor);

        Builder externalOperationId(String externalOperationId);

        BookHistoryItem build();
    }

    static Builder builder()
    {
        return new BookHistoryItemBuilder();
    }
}