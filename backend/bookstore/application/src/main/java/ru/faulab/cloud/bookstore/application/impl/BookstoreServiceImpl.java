package ru.faulab.cloud.bookstore.application.impl;

import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.collection.TreeMap;
import io.vavr.control.Option;
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
    public CompletionStage<Book> findBookById(String id)
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

    @Override
    public CompletionStage<Void> deleteBookById(String id)
    {
        this.books = this.books.remove(id);
        return CompletableFuture.completedStage(null);
    }

    @Override
    public CompletionStage<Book> updateBookById(String id, NewBook book)
    {
        Tuple2<Option<Book>, ? extends Map<String, Book>> result = this.books.computeIfPresent(id, (key, oldBook) -> Book.builder(book).id(UUID.randomUUID().toString()).build());
        this.books = result._2;
        return result._1.toCompletableFuture();
    }
}
