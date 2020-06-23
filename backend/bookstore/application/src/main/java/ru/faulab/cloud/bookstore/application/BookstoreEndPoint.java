package ru.faulab.cloud.bookstore.application;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.links.*;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ru.faulab.cloud.bookstore.application.api.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.*;
import java.util.List;
import java.util.concurrent.*;

@ApplicationScoped
@Path("v1/bookstore")
public class BookstoreEndPoint
{
    @Inject
    ManagedExecutor managedExecutor;

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    @NotNull
    @Operation(summary = "Ping", operationId = "ping")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Success",
                    links = @Link(name = "Echo response", operationId = "echo",
                            parameters = @LinkParameter(name = "text", expression = "$method")
                    ))
    })
    @Tag(name = "Temp")
    public CompletionStage<String> ping(
            @Parameter(description = "Who", example = "Zlo")
            @Valid @NotNull
            @QueryParam("who") String who)
    {
        Instant requestCome = Instant.now();
        return CompletableFuture.supplyAsync(() -> {
            long delay = Duration.between(requestCome, Instant.now()).toNanos();
            return "Hi `" + who + "` after: " + delay + " nano";
        }, managedExecutor);
    }

    @GET
    @Path("ping/{text}")
    @Produces(MediaType.TEXT_PLAIN)
    @NotNull
    @Operation(summary = "Echo", operationId = "echo")
    @Tag(name = "Temp")
    public CompletionStage<String> echo(
            @Parameter(description = "Text",
                    examples = {
                            @ExampleObject(name = "Simple", value = "simple"),
                            @ExampleObject(name = "Hard", value = "hard")
                    }
            )
            @Valid @NotNull
            @PathParam("text") String text)
    {
        return CompletableFuture.supplyAsync(() -> "Echo: " + text, managedExecutor);
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    @NotNull
    @Operation(summary = "List books", operationId = "list-books")
    @Tag(name = "Books")
    public CompletionStage<List<Book>> list(/*filter, limits, paging*/)
    {
        return CompletableFuture.supplyAsync(() -> List.of(Book.builder().id("id").build()));
    }
}
