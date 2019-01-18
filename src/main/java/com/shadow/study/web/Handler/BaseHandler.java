package com.shadow.study.web.Handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class BaseHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","text/html;charset=UTF-8");
    }
}
