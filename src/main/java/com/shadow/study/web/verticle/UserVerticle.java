package com.shadow.study.web.verticle;

import com.shadow.study.web.ApiHandler;
import com.shadow.study.web.Handler.UserHandler;
import com.shadow.study.web.RouterVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;

public class UserVerticle extends AbstractVerticle implements RouterVerticle {

    @Override
    public void start() {
        ApiHandler getUserHandle = new ApiHandler(HttpMethod.GET, "/getUser", new UserHandler());

        routers.add(getUserHandle);
    }
}
