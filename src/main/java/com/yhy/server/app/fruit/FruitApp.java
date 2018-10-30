package com.yhy.server.app.fruit;

import com.yhy.server.gourd.Gourd;
import com.yhy.server.gourd.annotation.Application;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 13:02
 * version: 1.0.0
 * desc   :
 */
@Application(port = 65520, packages = "com.yhy.server.app.fruit.servlet")
public class FruitApp {

    public static void main(String[] args) {
        new Gourd().start();
    }
}
