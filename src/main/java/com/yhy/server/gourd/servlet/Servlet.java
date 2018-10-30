package com.yhy.server.gourd.servlet;

import com.yhy.server.gourd.request.Request;
import com.yhy.server.gourd.response.Response;

import java.io.IOException;
import java.util.Locale;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:08
 * version: 1.0.0
 * desc   :
 */
public abstract class Servlet {

    public void init() {
    }

    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request, Response response);

    public void service(Request request, Response response) throws IOException {
        switch (request.getMethod().toLowerCase(Locale.getDefault())) {
            case "get":
                doGet(request, response);
                break;
            case "post":
                doPost(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }

        destroy(request, response);
    }

    public void destroy(Request request, Response response) throws IOException {
        request.close();
        response.close();
    }
}
