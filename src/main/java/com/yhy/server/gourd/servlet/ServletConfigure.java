package com.yhy.server.gourd.servlet;

import com.yhy.server.gourd.annotation.Application;
import com.yhy.server.gourd.annotation.ServletMapper;
import com.yhy.server.gourd.listener.OnInitializeListener;
import com.yhy.server.gourd.utils.AnnotationUtils;
import com.yhy.server.gourd.utils.ArrayUtils;
import com.yhy.server.gourd.utils.CollectionUtils;
import com.yhy.server.gourd.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:15
 * version: 1.0.0
 * desc   :
 */
@SuppressWarnings("unchecked")
public class ServletConfigure implements OnInitializeListener {
    private int port;
    private Map<String, ServletMapping> config;

    public ServletConfigure() {
        config = new HashMap<>();
    }

    private static Class<?> getMainProcess() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if ("main".equalsIgnoreCase(element.getMethodName())) {
                System.out.println("当前的入口类为：" + element.getClassName());
                try {
                    return Class.forName(element.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public int getPort() {
        return port;
    }

    public Map<String, ServletMapping> getConfig() {
        return config;
    }

    @Override
    public void onInitialize() {
        Class<?> mainClass = getMainProcess();
        if (null != mainClass) {

            Application application = mainClass.getAnnotation(Application.class);
            port = application.port();

            String[] packages = application.packages();
            List<Class<?>> servletList = new ArrayList<>();
            List<Class<?>> temp;
            if (ArrayUtils.isNotEmpty(packages)) {
                for (String pkg : packages) {
                    temp = AnnotationUtils.getClassList(pkg, ServletMapper.class);
                    if (CollectionUtils.isNotEmpty(temp)) {
                        servletList.addAll(temp);
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(servletList)) {
                String name = null, url = null;
                ServletMapper mapper;
                for (Class<?> clazz : servletList) {
                    if (clazz.getSuperclass() != Servlet.class) {
                        continue;
                    }
                    mapper = clazz.getAnnotation(ServletMapper.class);
                    if (null != mapper) {
                        name = StringUtils.isNotEmpty(mapper.name()) ? mapper.name() : clazz.getCanonicalName();
                        url = StringUtils.isNotEmpty(mapper.url()) ? mapper.url() : mapper.value();
                    }
                    if (StringUtils.isNotEmpty(name)) {
                        config.put(name, new ServletMapping(name, url, (Class<Servlet>) clazz));
                    }
                }
            }
        }
    }
}
