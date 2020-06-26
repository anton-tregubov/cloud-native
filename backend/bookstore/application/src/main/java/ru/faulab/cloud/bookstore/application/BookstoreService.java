package ru.faulab.cloud.bookstore.application;

import ru.faulab.cloud.bookstore.application.api.*;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface BookstoreService
{
    CompletionStage<List<Book>> findBooksByCondition();

    CompletionStage<Book> findBooksById(String id);

    CompletionStage<Book> createBook(NewBook book);
}
