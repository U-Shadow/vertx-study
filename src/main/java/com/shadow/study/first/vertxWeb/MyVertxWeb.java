package com.shadow.study.first.vertxWeb;

import com.shadow.study.first.router.UserRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MyVertxWeb extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        // 创建router
        Router router = Router.router(vertx);

        router.route("/").handler(routingContext -> {
            HttpServerResponse httpServerResponse = routingContext.response();
            httpServerResponse.putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        router.route("/test").handler(routingContext -> {
            HttpServerResponse httpServerResponse = routingContext.response();
            httpServerResponse.putHeader("content-type", "text/html")
                    .end("<h1>Hello Test</h1>");
        });

        router.route("/assets/*").handler(StaticHandler.create("assets"));

        router.mountSubRouter("/getUser", new UserRouter().getUserRouter());

        vertx.createHttpServer().requestHandler(router)
                .listen(config().getInteger("http.port",8888), result -> {
                    if(result.succeeded()) {
                        fut.complete();
                    }
                    else {
                        fut.fail(result.cause());
                    }
                });
    }
}
