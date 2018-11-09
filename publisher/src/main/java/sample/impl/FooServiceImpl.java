package sample.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import sample.FooService;

public class FooServiceImpl implements FooService {
    @Override
    public void getString(Handler<AsyncResult<String>> resultHandler) {
        resultHandler.handle(Future.succeededFuture("hello from foo service"));
    }
}
