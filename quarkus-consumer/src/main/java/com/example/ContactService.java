package com.example;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "contacts-api")
@Path("/test")
public interface ContactService {

    @POST
    Uni<Contact> createContactAsync(Contact contact);
}
