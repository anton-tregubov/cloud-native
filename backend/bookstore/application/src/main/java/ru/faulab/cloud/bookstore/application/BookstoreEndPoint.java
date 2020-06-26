package ru.faulab.cloud.bookstore.application;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.links.Link;
import org.eclipse.microprofile.openapi.annotations.links.*;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ru.faulab.cloud.bookstore.application.api.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
@Path("v1/bookstore")
@Tag(name = "Books")
public class BookstoreEndPoint
{
    @Inject
    BookstoreService bookstoreService;

    @Inject
    ManagedExecutor managedExecutor;

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List books", operationId = "list-books")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Success",
                    links = {
                            @Link(name = "Get book", operationId = "get-book", parameters = @LinkParameter(name = "id", expression = "$response.body#/data/0/id"))
                    })
    })
    public CompletionStage<List<Book>> list(/*filter, limits, paging*/)
    {
        return bookstoreService.findBooksByCondition();
    }

    @GET
    @Path("books/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get book", operationId = "get-book")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Success",
                    links = {
                            @Link(name = "Delete book", operationId = "delete-book", parameters = @LinkParameter(name = "id", expression = "$response.body#/data/0/id")),
                            @Link(name = "Update book", operationId = "update-book", parameters = @LinkParameter(name = "id", expression = "$response.body#/data/0/id"))
                    })
    })
    public CompletionStage<Book> read(@PathParam("id") @Parameter(description = "Book ID") String id)
    {
        return bookstoreService.findBookById(id);
    }

    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create book", operationId = "create-book")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Success",
                    links = {
                            @Link(name = "Get book", operationId = "get-book", parameters = @LinkParameter(name = "id", expression = "$response.header.location"))
                    })
    })
    public CompletionStage<Response> create(@Valid @Parameter NewBook book)
    {
        return bookstoreService.createBook(book)
                .thenApplyAsync(createBook -> Response.created(UriBuilder.fromMethod(BookstoreEndPoint.class, "read").build(createBook.getId())).build(), managedExecutor);
    }

    @DELETE
    @Path("books/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete book", operationId = "delete-book")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Success")
    })
    public CompletionStage<Response> delete(@PathParam("id") @Parameter(description = "Book ID") String id)
    {
        return bookstoreService.deleteBookById(id)
                .thenApplyAsync(createBook -> Response.ok().build(), managedExecutor);
    }

    @PUT
    @Path("books/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NotNull
    @Operation(summary = "Update book", operationId = "update-book")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Success")
    })
    public CompletionStage<Response> update(@PathParam("id") @Parameter(description = "Book ID") String id, @Valid @Parameter NewBook book)
    {
        return bookstoreService.updateBookById(id, book)
                .thenApplyAsync(createBook -> Response.ok().links().build(), managedExecutor);
    }
}
