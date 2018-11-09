package sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

public class EventBusVertical extends AbstractVerticle {

  @Override
  public void start() {

      final JsonObject config = config();

      this.vertx.eventBus().<JsonObject>consumer("from.worker")
              .handler(m -> {
                  System.out.println("Received: " + m.body());
              });

      DeploymentOptions deploymentOptions = new DeploymentOptions().setWorker(true).setConfig(config);
      this.vertx.deployVerticle(WorkerVertical.class.getName(), deploymentOptions);




  }
}