package com.yhy.server.app.car;

import com.yhy.server.gourd.Gourd;
import com.yhy.server.gourd.annotation.Application;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 11:08
 * version: 1.0.0
 * desc   :
 */
@Application(port = 10240, packages = {"com.yhy.server.app.car.servlet"})
public class CarApp {

    public static void main(String[] args) {
        new Gourd().start();
    }
}
