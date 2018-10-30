package com.yhy.server.app.fruit.servlet;

import com.yhy.server.gourd.annotation.ServletMapper;
import com.yhy.server.gourd.request.Request;
import com.yhy.server.gourd.response.Response;
import com.yhy.server.gourd.servlet.Servlet;

import java.io.IOException;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 13:03
 * version: 1.0.0
 * desc   :
 */
@ServletMapper("/fruit/apple")
public class ServletApple extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        try {
            response.write("这是苹果");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
    }
}
