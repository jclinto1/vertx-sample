package sample.impl;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class AddrPublisherVertical extends AbstractVerticle {
    @Override
    public void start() {
        vertx.setPeriodic(1000, l -> {
            System.out.println("Sending...");
            vertx.eventBus().publish("sample.address", new JsonObject().put("name","john.doe"));
        });
    }
}