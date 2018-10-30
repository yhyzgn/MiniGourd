package com.yhy.server.gourd.utils;

import java.util.Collection;
import java.util.Map;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:32
 * version: 1.0.0
 * desc   :
 */
public class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException("Can not instantiate utils class");
    }

    public static boolean isEmpty(Collection<?> source) {
        return null == source || source.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> source) {
        return !isEmpty(source);
    }

    public static boolean isEmpty(Map<?, ?> source) {
        return null == source || source.size() == 0;
    }

    public static boolean isNotEmpty(Map<?, ?> source) {
        return !isEmpty(source);
    }
}
