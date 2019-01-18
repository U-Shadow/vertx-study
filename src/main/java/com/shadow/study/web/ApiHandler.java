package com.shadow.study.web;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public class ApiHandler {

    private HttpMethod httpMethod;

    private String api;

    private Handler<RoutingContext> handler;

    public ApiHandler(HttpMethod httpMethod, String api, Handler<RoutingContext> handler) {
        this.httpMethod = httpMethod;
        this.api = api;
        this.handler = handler;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Handler<RoutingContext> getHandler() {
        return handler;
    }

    public void setHandler(Handler<RoutingContext> handler) {
        this.handler = handler;
    }
}
