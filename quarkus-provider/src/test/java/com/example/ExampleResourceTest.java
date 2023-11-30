package com.example;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@QuarkusTest
@Provider("quarkus-provider")
@PactBroker(url = "http://localhost:9292", authentication = @PactBrokerAuth(username = "pactUser", password = "pactPass"))
public class ExampleResourceTest {

    @TestTarget
    HttpTestTarget target = new HttpTestTarget("http://localhost:9292");

    @ExtendWith(PactVerificationInvocationContextProvider.class)
    @TestTemplate
    public void pactTest(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void beforeEach(PactVerificationContext context) {
        context.setTarget(target);
    }

    @State("CreateContact")
    public void createContact() {

    }

}