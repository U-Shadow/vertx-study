package com.shadow.study.web.datasource;

import com.shadow.study.first.utils.FileUtils;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLClient;

import java.io.InputStream;

public class DBSource {

    private SQLClient sqlClient;
    private static JsonObject config;

    static {
        System.out.println("===== 读取数据源json =====");
        InputStream is = DBSource.class.getClassLoader().getResourceAsStream("datasource.json");
        String input = FileUtils.InputStream2String(is, null);
        config = new JsonObject(input);
    }

    public DBSource(Vertx vertx, String dsName) {
        JsonObject dbConfig = config.getJsonObject(dsName);
        if(dbConfig == null) {
            throw new RuntimeException("没有找到指定的数据源");
        }
        sqlClient = MySQLClient.createShared(vertx, dbConfig);
    }

    public DBSource(Vertx vertx) {
        this(vertx, "default");
    }

    public SQLClient getSqlClient() {
        return sqlClient;
    }
}
