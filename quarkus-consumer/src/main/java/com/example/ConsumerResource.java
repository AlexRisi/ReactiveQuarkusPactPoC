package com.example;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/test")
public class ConsumerResource {

    @RestClient
    ContactService service;

    @GET
    public Uni<String> hello() {
        return Uni.createFrom().item("Hello!");
    }

    @GET
    @Path("/async")
    public Uni<Contact> createContact() {
        var contact = new Contact("name test", "address test", "phone test");
        return service.createContactAsync(contact);
    }



}
