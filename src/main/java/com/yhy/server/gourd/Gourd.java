package com.yhy.server.gourd;

import com.yhy.server.gourd.request.Request;
import com.yhy.server.gourd.response.Response;
import com.yhy.server.gourd.servlet.Servlet;
import com.yhy.server.gourd.servlet.ServletConfigure;
import com.yhy.server.gourd.servlet.ServletMapping;
import com.yhy.server.gourd.utils.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:44
 * version: 1.0.0
 * desc   :
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Gourd {

    ServletConfigure config;

    private Map<String, Class<Servlet>> servletMap = new HashMap<>();

    public Gourd() {
        config = new ServletConfigure();
        init();
    }

    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(config.getPort());
            System.out.println("Gourd server has been started at port : " + config.getPort() + " ...");

            while (true) {
                Socket client = server.accept();

                InputStream inputStream = client.getInputStream();
                OutputStream outputStream = client.getOutputStream();

                Request request = new Request(inputStream);
                Response response = new Response(request, outputStream);

                dispatch(request, response);

                if (!client.isClosed()) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != server && !server.isClosed()) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init() {
        // 初始化
        config.onInitialize();

        // 获取url与类
        if (CollectionUtils.isNotEmpty(config.getConfig())) {
            ServletMapping mapping;
            for (Map.Entry<String, ServletMapping> et : config.getConfig().entrySet()) {
                mapping = et.getValue();
                servletMap.put(mapping.getUrl(), mapping.getClazz());
                System.out.println(mapping);
            }
        }
    }

    private void dispatch(Request request, Response response) {
        Class<Servlet> clazz = servletMap.get(request.getUrl());
        if (null != clazz) {
            try {
                Servlet servlet = clazz.newInstance();
                servlet.service(request, response);
            } catch (InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
