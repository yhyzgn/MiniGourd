package com.yhy.server.gourd.annotation;

import java.lang.annotation.*;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:17
 * version: 1.0.0
 * desc   :
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ServletMapper {

    String name() default "";

    String url() default "";

    String value() default "";
}
