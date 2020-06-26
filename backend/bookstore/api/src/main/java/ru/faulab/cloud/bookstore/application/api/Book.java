package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.immutables.value.Value;

import javax.validation.constraints.*;

@Value.Immutable
@JsonSerialize(as = BookBuilder.ImmutableBook.class)
@JsonDeserialize(as = BookBuilder.ImmutableBook.class)
@Schema(description = "Real physical book instance")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Book.class)
public interface Book extends AbstractBook
{
    @NotNull
    @NotBlank
    @Schema(description = "Book unique identifier", example = "wfvWEFwer")
    String getId();

    interface Builder extends AbstractBook.Builder<Builder>
    {
        Builder id(String id);

        Book build();
    }

    static Builder builder()
    {
        return new BookBuilder();
    }

    static Builder builder(NewBook newBook)
    {
        return builder()
                .title(newBook.getTitle())
                .authors(newBook.getAuthors())
                .publisher(newBook.getPublisher())
                .pageCount(newBook.getPageCount())
                .publishYear(newBook.getPublishYear());
    }
}
