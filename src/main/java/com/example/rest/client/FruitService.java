package com.example.rest.client;

import com.example.model.Fruit;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;

@Path("/fruits")
@RegisterRestClient(configKey="fruit-api")
public interface FruitService {

    @GET
    List<Fruit> getAllFruits();
}
