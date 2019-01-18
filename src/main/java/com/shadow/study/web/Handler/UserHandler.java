package com.shadow.study.web.Handler;

import com.shadow.study.web.datasource.DBSource;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.RoutingContext;

public class UserHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        SQLClient sqlClient = new DBSource(routingContext.vertx()).getSqlClient();

        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","application/json; charset=UTF-8");
        sqlClient.query("select * from user", r -> {
            if (r.succeeded()) {
                System.out.println(Json.encodePrettily(r.result().getRows()));
                response.end(r.result().getRows().toString());
            } else {
                response.end(r.cause().getMessage());
            }
            sqlClient.close();
        });
    }
}
