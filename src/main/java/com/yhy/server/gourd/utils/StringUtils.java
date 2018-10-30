package com.yhy.server.gourd.utils;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 9:36
 * version: 1.0.0
 * desc   :
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("Can not instantiate utils class");
    }

    public static boolean isEmpty(String source) {
        return null == source || source.length() == 0;
    }

    public static boolean isNotEmpty(String source) {
        return !isEmpty(source);
    }
}
