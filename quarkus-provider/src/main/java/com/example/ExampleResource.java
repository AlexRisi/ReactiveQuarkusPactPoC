package com.example;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/test")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String userCreated() {
        return "User Created!";
    }

    @POST
    @ResponseStatus(value = 201)
    public Contact createContact(Contact contact) {
        System.out.println("Creating User...");
        System.out.println(contact.name);
        System.out.println(contact.address);
        System.out.println(contact.phone);
        System.out.println("-------------------------------------");
//        return Uni.createFrom().item(contact);
        return contact;
    }

    record Contact(String name, String address, String phone) {}
}
