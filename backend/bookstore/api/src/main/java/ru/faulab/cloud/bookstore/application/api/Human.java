package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.immutables.value.Value;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Value.Immutable
@JsonSerialize(as = HumanBuilder.ImmutableHuman.class)
@JsonDeserialize(as = HumanBuilder.ImmutableHuman.class)
@Schema(description = "Short information about real human")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public interface Human extends Serializable
{
    @NotNull
    @NotBlank
    @Schema(description = "Human unique identifier", example = "wfvWEFwer")
    String getId();

    @NotNull
    @NotBlank
    @Schema(description = "Human FIO", example = "Лев Николаевич Толстой")
    String getFullName();

    @NotNull
    @PastOrPresent
    @Schema(description = "Human birthday", example = " 9-сентября-1828")
    LocalDate getBirthday();

    interface Builder
    {
        Builder id(String id);

        Builder fullName(String fullName);

        Builder birthday(LocalDate birthday);

        Human build();
    }

    static Builder builder()
    {
        return new HumanBuilder();
    }
}
