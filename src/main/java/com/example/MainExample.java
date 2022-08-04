package com.example;

import com.example.model.Fruit;
import com.example.rest.client.FruitResource;
import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;
import java.util.List;

@QuarkusMain
public class MainExample implements QuarkusApplication {

    @Inject
    FruitResource fruitResource;

    @Override
    public int run(String... args) throws Exception {
        Log.info("Calling getById");
        List<Fruit> fruits = fruitResource.getAllFruits();
        Log.info(fruits);
        return 0;
    }
}
