package sample.impl;

import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import sample.FooService;
import sample.common.MicroServiceVerticle;

public class FooVertical extends MicroServiceVerticle {
    @Override
    public void start() {
        super.start();

        FooServiceImpl service = new FooServiceImpl();
        ProxyHelper.registerService(FooService.class, vertx, service, "service.foo");

        publishEventBusService("foo", "service.foo", FooService.class, ar -> {
            if (ar.failed()) {
                ar.cause().printStackTrace();
            } else {
                System.out.println("Foo service published : " + ar.succeeded());
            }
        });
        publishMessageSource("foo-events", "foo", ar -> {
            if (ar.failed()) {
                ar.cause().printStackTrace();
            } else {
                System.out.println("Foo Events service published : " + ar.succeeded());
            }
        });

        vertx.setPeriodic(1000, l -> {
            System.out.println("Sending...");
            vertx.eventBus().publish("sample.address", new JsonObject().put("name","john.doe"));
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}