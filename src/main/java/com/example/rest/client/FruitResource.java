package com.example.rest.client;

import com.example.model.Fruit;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {

    @Inject
    @RestClient
    FruitService fruitService;

    @GET
    public List<Fruit> getAllFruits() {
        return fruitService.getAllFruits();
    }

}
