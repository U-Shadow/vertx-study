package com.shadow.study.web;

import com.shadow.study.first.utils.FileUtils;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class StartVerticle extends AbstractVerticle implements RouterVerticle {

    public static void main(String[] args) {
        InputStream is =  StartVerticle.class.getClassLoader().getResourceAsStream("conf/conf.json");
        String input = FileUtils.InputStream2String(is, null);
        JsonObject config = new JsonObject(input);
        Vertx vertx = Vertx.vertx();
//        new DBSource(vertx);
        vertx.deployVerticle(new StartVerticle(), new DeploymentOptions().setConfig(config));
    }

    @Override
    public void start() throws Exception {
        super.start();
        JsonArray verticles = config().getJsonArray("verticles");

        List<Future> futures = verticles.stream()
                .map(name -> Future.<String>future(r ->
                        vertx.deployVerticle(name.toString(), new DeploymentOptions().setConfig(config()), r))
                ).collect(Collectors.toList());

        CompositeFuture.all(futures).setHandler(res -> {
            Router router = Router.router(vertx);
            routers.forEach(r ->
                    router.route(r.getHttpMethod(), r.getApi()).handler(r.getHandler())
            );
            System.out.println(routers.size());
            vertx.createHttpServer().requestHandler(router).listen(config().getInteger("http.port"), server -> {
                if (server.succeeded()) {
                    System.out.println("server ok");
                } else {
                    server.cause().printStackTrace();
                }
            });
        });
    }

}
