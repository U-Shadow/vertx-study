package com.shadow.study.web;

import java.util.ArrayList;
import java.util.List;

public interface RouterVerticle {
    List<ApiHandler> routers = new ArrayList<>();
}
