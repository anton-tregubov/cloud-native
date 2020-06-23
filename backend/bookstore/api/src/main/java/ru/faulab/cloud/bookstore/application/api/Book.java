package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.databind.annotation.*;
import org.immutables.value.Value;

import javax.validation.constraints.*;
import java.time.Year;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = BookBuilder.ImmutableBook.class)
@JsonDeserialize(as = BookBuilder.ImmutableBook.class)
public interface Book
{
    @NotNull
    @NotBlank
    String getId();

    @NotNull
    @NotBlank
    String getTitle();

    @NotNull
    @NotEmpty
    List<Human> getAuthors();

    @NotNull
    Publisher getPublisher();

    @NotNull
    @PastOrPresent Year getPublishYear();

    @NotNull
    @Positive
    int getPageCount();

    interface Builder
    {
        Builder id(String id);

        Builder title(String title);

        Builder addAuthors(Human... elements);

        Builder addAuthors(Iterable<? extends Human> elements);

        Builder authors(Iterable<? extends Human> elements);

        Builder publisher(Publisher publisher);

        Builder publishYear(Year publishYear);

        Builder pageCount(int pageCount);

        Book build();
    }

    static Builder builder()
    {
        return new BookBuilder();
    }
}
