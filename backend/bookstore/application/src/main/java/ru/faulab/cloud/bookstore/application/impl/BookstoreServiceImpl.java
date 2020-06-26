package ru.faulab.cloud.bookstore.application.impl;

import io.vavr.collection.Map;
import io.vavr.collection.TreeMap;
import ru.faulab.cloud.bookstore.application.BookstoreService;
import ru.faulab.cloud.bookstore.application.api.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;

@ApplicationScoped
public class BookstoreServiceImpl implements BookstoreService
{
    private Map<String, Book> books;

    public BookstoreServiceImpl()
    {
        books = TreeMap.empty();
    }

    @Override

    public CompletionStage<List<Book>> findBooksByCondition()
    {
        return CompletableFuture.completedStage(books.values().asJava());
    }

    @Override
    public CompletionStage<Book> findBooksById(String id)
    {
        return this.books.get(id).toCompletableFuture();
    }

    @Override
    public CompletionStage<Book> createBook(NewBook book)
    {
        Book createBook = Book.builder(book).id(UUID.randomUUID().toString()).build();
        this.books = this.books.put(createBook.getId(), createBook);
        return CompletableFuture.completedStage(createBook);
    }
}
