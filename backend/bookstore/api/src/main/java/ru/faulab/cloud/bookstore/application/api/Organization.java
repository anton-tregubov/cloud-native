package ru.faulab.cloud.bookstore.application.api;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.immutables.value.Value;

import javax.validation.constraints.*;
import java.io.Serializable;

@Value.Immutable
@JsonSerialize(as = OrganizationBuilder.ImmutableOrganization.class)
@JsonDeserialize(as = OrganizationBuilder.ImmutableOrganization.class)
@Schema(description = "Short information about real organization")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Organization.class)
public interface Organization extends Serializable
{
    @NotNull
    @NotBlank
    @Schema(description = "Organization unique identifier", example = "wfvWEFwer")
    String getId();

    @NotNull
    @NotBlank
    @Schema(description = "Organization physical name", example = "ИЗДАЛ")
    String getName();

    interface Builder
    {
        Builder id(String id);

        Builder name(String name);

        Organization build();
    }

    static Builder builder()
    {
        return new OrganizationBuilder();
    }
}