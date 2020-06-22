package ru.faulab.cloud.bookstore.application;

import org.eclipse.microprofile.openapi.*;
import org.eclipse.microprofile.openapi.models.*;

public class OpenApiEnricher implements OASFilter
{
    @Override
    public Operation filterOperation(Operation operation)
    {
        operation.getResponses()
                .addAPIResponse("401", OASFactory.createAPIResponse().description("Not Authorized"))
                .addAPIResponse("403", OASFactory.createAPIResponse().description("Bad Request"))
                .addAPIResponse("500", OASFactory.createAPIResponse().description("Server Error"));
        return operation;
    }

    @Override
    public void filterOpenAPI(OpenAPI openAPI)
    {
        openAPI.getInfo().version("42");
    }
}
