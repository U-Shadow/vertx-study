package com.shadow.study.first.router;

import com.shadow.study.web.Handler.UserHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

public class UserRouter extends AbstractVerticle {

    private Router userRouter;

    public UserRouter() {
        System.out.println("+++++");
        userRouter = Router.router(vertx);
    }

    @Override
    public void start(Future<Void> fut) {
        System.out.println("================");
        UserHandler userHandler = new UserHandler();
        userRouter.get("/getUser").handler(userHandler);
    }

    public Router getUserRouter() {
        return userRouter;
    }

    public void setUserRouter(Router userRouter) {
        this.userRouter = userRouter;
    }
}
