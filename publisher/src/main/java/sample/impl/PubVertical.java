package sample.impl;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import sample.impl.AddrPublisherVertical;

public class PubVertical extends AbstractVerticle {
    @Override
    public void start() {
        final JsonObject config = config();

        DeploymentOptions deploymentOptions1 = new DeploymentOptions().setConfig(config);

        this.vertx.deployVerticle(AddrPublisherVertical.class.getName(), deploymentOptions1, ar -> {
            if(ar.succeeded()) {
                System.out.println("Service: AddrPublisherVertical deployed");
            }
        });

        DeploymentOptions deploymentOptions2 = new DeploymentOptions().setConfig(config);

        this.vertx.deployVerticle(FooVertical.class.getName(), deploymentOptions2, ar -> {
            if(ar.succeeded()) {
                System.out.println("Service: FooVertical deployed");
            }
        });
    }
}