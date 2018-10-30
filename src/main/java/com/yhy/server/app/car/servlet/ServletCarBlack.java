package com.yhy.server.app.car.servlet;

import com.yhy.server.gourd.annotation.ServletMapper;
import com.yhy.server.gourd.request.Request;
import com.yhy.server.gourd.response.Response;
import com.yhy.server.gourd.servlet.Servlet;

import java.io.IOException;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 11:11
 * version: 1.0.0
 * desc   :
 */
@ServletMapper(name = "blackCar", url = "/car/black")
public class ServletCarBlack extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        try {
            response.write("method : get \r car : 这个黑车   哈哈哈哈");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        doGet(request, response);
    }
}
