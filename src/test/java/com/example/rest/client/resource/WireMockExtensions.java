package com.example.rest.client.resource;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
public class WireMockExtensions implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;
    private static final int WIREMOCK_PORT = 7777;
    private static final String FRUITS_JSON_FILE = "/fruits.json";
    private static final String BASE_PATH = "/fruits";
    
    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(WIREMOCK_PORT);
        wireMockServer.start();
        try (InputStream is = WireMockExtensions.class.getResourceAsStream(FRUITS_JSON_FILE)) {
        	String fruits = new String(is.readAllBytes());
        	wireMockServer.stubFor(get(urlEqualTo(BASE_PATH))
                    .willReturn(okJson(fruits)));
        } catch (IOException e) {
            fail("Could not configure Wiremock server. Caused by: " + e.getMessage());
        }
        return Collections.singletonMap("quarkus.rest-client.fruit-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
