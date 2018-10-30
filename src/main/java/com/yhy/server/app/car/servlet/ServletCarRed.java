package com.yhy.server.app.car.servlet;

import com.yhy.server.gourd.annotation.ServletMapper;
import com.yhy.server.gourd.request.Request;
import com.yhy.server.gourd.response.Response;
import com.yhy.server.gourd.servlet.Servlet;

import java.io.IOException;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 11:09
 * version: 1.0.0
 * desc   :
 */
@ServletMapper("/car/red")
public class ServletCarRed extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        doPost(request, response);
    }

    @Override
    public void doPost(Request request, Response response) {
        try {
            response.write("method : post \r car : red");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
