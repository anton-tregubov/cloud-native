package ru.faulab.cloud.bookstore.application.api;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

public interface AbstractBook extends Serializable
{
    @NotNull
    @NotBlank
    @Schema(description = "Book title", example = "Война и Мир")
    String getTitle();

    @NotNull
    @NotEmpty
    @Schema(description = "Book author")
    List<Human> getAuthors();

    @NotNull
    @Schema(description = "Book publisher")
    Organization getPublisher();

    @NotNull
    @PastOrPresent
    @Schema(description = "Book publish year", example = "2020", type = SchemaType.STRING)
    Year getPublishYear();

    @NotNull
    @Positive
    @Schema(description = "Book page count", example = "42")
    int getPageCount();

    interface Builder<Self extends Builder<Self>>
    {
        Self title(String title);

        Self addAuthors(Human... elements);

        Self addAuthors(Iterable<? extends Human> elements);

        Self authors(Iterable<? extends Human> elements);

        Self publisher(Organization publisher);

        Self publishYear(Year publishYear);

        Self pageCount(int pageCount);
    }
}
