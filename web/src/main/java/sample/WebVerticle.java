package sample;

import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import sample.common.MicroServiceVerticle;

public class WebVerticle extends MicroServiceVerticle {

    @Override
    public void start(Future<Void> future) {
        super.start();

        Router router = Router.router(vertx);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions();
        options.addOutboundPermitted(new PermittedOptions().setAddress("service.foo"))
                .addInboundPermitted(new PermittedOptions().setAddress("service.foo"));

        sockJSHandler.bridge(options);
        router.route("/eventbus/*").handler(sockJSHandler);

//        ServiceDiscoveryRestEndpoint.create(router, discovery);

        router.route("/*").handler(StaticHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, ar -> {
                    if (ar.failed()) {
                        future.fail(ar.cause());
                    } else {
                        future.complete();
                    }
                });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

