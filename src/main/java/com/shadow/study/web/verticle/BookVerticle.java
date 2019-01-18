package com.shadow.study.web.verticle;

import com.shadow.study.web.ApiHandler;
import com.shadow.study.web.RouterVerticle;
import com.shadow.study.web.datasource.DBSource;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.sql.SQLClient;

import java.util.Arrays;

public class BookVerticle extends AbstractVerticle implements RouterVerticle {

    @Override
    public void start(){
//        super.start();
        /*JsonObject mysqlConfig = new JsonObject().put("host", "127.0.0.1")
                .put("port", 3306).put("maxPollSize", 10)
                .put("username", "root").put("password", "root")
                .put("database", "vertxdb");
        sqlClient = MySQLClient.createShared(vertx, mysqlConfig);*/

        SQLClient sqlClient = new DBSource(vertx).getSqlClient();
        routers.addAll(Arrays.asList(
                new ApiHandler(HttpMethod.GET, "/b1", res -> {
                    HttpServerResponse response = res.response();
                    // 设置编码
                    response.putHeader("content-type","text/html;charset=UTF-8");
                    sqlClient.query("select * from book", r -> {
                        if (r.succeeded()) {
                            System.out.println(Json.encodePrettily(r.result().getRows()));
                            response.end(r.result().getRows().toString());
                        } else {
                            response.end(r.cause().getMessage());
                        }
                        sqlClient.close();
                    });
                }),
                new ApiHandler(HttpMethod.GET, "/b2", res -> {
                    System.out.println(vertx.toString());
                    sqlClient.close();
                    res.response().end("b2 ok");
                })
        ));
    }
}
