package com.shadow.study.first;

import com.shadow.study.first.utils.FileUtils;
import com.shadow.study.first.vertxWeb.MyVertxWeb;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.io.InputStream;

public class EngineStartup {

    private static Vertx vertx;

    public static void main(String[] args) {
        ClassLoader classLoader = EngineStartup.class.getClassLoader();
        // 初始化本地vertx容器
        vertx = Vertx.vertx();
        InputStream is = classLoader.getResourceAsStream("conf/conf.json");
        String input = FileUtils.InputStream2String(is, null);
        JsonObject config = new JsonObject(input);

        DeploymentOptions options = new DeploymentOptions().setConfig(config);

        vertx.deployVerticle(MyVertxWeb.class.getName(), options, result -> {
            if(result.succeeded()) {
                System.out.println("vertx 启动成功");
            }
        });
    }
}
