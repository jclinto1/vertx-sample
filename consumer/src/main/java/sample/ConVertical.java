package sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class ConVertical extends AbstractVerticle {

  @Override
  public void start() {
    this.vertx.eventBus().<JsonObject>consumer("sample.address")
            .handler(m -> {
              System.out.println("Received: " + m.body());
            });
  }
}