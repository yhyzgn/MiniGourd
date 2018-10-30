package com.yhy.server.gourd.servlet;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:13
 * version: 1.0.0
 * desc   :
 */
public class ServletMapping {
    private String name;
    private String url;
    private Class<Servlet> clazz;

    public ServletMapping(String name, String url, Class<Servlet> clazz) {
        this.name = name;
        this.url = url;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<Servlet> getClazz() {
        return clazz;
    }

    public void setClazz(Class<Servlet> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "ServletMapping{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
