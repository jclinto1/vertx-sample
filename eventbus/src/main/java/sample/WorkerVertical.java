package sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

public class WorkerVertical extends AbstractVerticle {

  @Override
  public void start() {

// Works fine
//      this.vertx.setPeriodic(1000, l -> {
//          vertx.eventBus().publish("from.worker", new JsonObject().put("key", "value"));
//      });


//Long running task doesn't behave the same
      while(true) {

          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          vertx.eventBus().publish("from.worker", new JsonObject().put("key", "value"));
      }
  }
}