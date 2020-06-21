package ru.faulab.cloud.bookstore.application;

import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.*;
import java.util.concurrent.*;

@ApplicationScoped
@Path("bookstore")
public class BookstoreEndPoint
{
    @Inject
    ManagedExecutor managedExecutor;

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    @NotNull
    public CompletionStage<String> ping()
    {
        Instant requestCome = Instant.now();
        return CompletableFuture.supplyAsync(() -> {
            long delay = Duration.between(requestCome, Instant.now()).toNanos();
            return "Hi after: " + delay + "nano";
        }, managedExecutor);
    }
}
