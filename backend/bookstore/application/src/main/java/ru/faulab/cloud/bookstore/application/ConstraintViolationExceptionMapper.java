package ru.faulab.cloud.bookstore.application;

import io.vavr.collection.Stream;

import javax.validation.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>
{
    @Override
    public Response toResponse(ConstraintViolationException exception)
    {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(Stream.ofAll(exception.getConstraintViolations()).map(ConstraintViolation::getMessage).toList())
                .build();
    }
}
