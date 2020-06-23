package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.immutables.value.Value;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = BookBuilder.ImmutableBook.class)
@JsonDeserialize(as = BookBuilder.ImmutableBook.class)
@Schema(description = "Real physical book instance")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public interface Book extends Serializable
{
    @NotNull
    @NotBlank
    @Schema(description = "Book unique identifier", example = "wfvWEFwer")
    String getId();

    @NotNull
    @NotBlank
    @Schema(description = "Book title", example = "Война и Мир")
    String getTitle();

    @NotNull
    @NotEmpty
    @Schema(description = "Book author", example = "[{'fullName': 'Лев Николаевич Толстой'}]")
    List<Human> getAuthors();

    @NotNull
    @Schema(description = "Book publisher", example = "{'name': 'ИЗДАЛ'}")
    Organization getPublisher();

    @NotNull
    @PastOrPresent
    @Schema(description = "Book publish year", example = "2020")
    Year getPublishYear();

    @NotNull
    @Positive
    @Schema(description = "Book page count", example = "42")
    int getPageCount();

    interface Builder
    {
        Builder id(String id);

        Builder title(String title);

        Builder addAuthors(Human... elements);

        Builder addAuthors(Iterable<? extends Human> elements);

        Builder authors(Iterable<? extends Human> elements);

        Builder publisher(Organization publisher);

        Builder publishYear(Year publishYear);

        Builder pageCount(int pageCount);

        Book build();
    }

    static Builder builder()
    {
        return new BookBuilder();
    }
}
