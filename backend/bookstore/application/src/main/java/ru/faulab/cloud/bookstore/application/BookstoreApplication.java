package ru.faulab.cloud.bookstore.application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/rest")
@OpenAPIDefinition(
        info = @Info(
                title = "Bookstore REST API",
                contact = @Contact(
                        name = "Anton Tregubov",
                        email = "tregubov@gmail.com"
                ),
                description = "Allow to store, append and remove books",
                license = @License(name = "MIT", url = "https://mit-license.org/"),
                version = "Add during build"
        ),
        tags = {
                @Tag(name = "Books", description = "Book CRUD operations"),
        }
)
public class BookstoreApplication extends Application
{
}
