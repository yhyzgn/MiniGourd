package com.yhy.server.gourd.annotation;

import java.lang.annotation.*;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 11:52
 * version: 1.0.0
 * desc   :
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Application {

    int port() default 1024;

    String[] packages() default {""};
}
