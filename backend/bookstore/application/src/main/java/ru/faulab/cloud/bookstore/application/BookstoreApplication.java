package ru.faulab.cloud.bookstore.application;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/rest")
public class BookstoreApplication extends Application
{
}
