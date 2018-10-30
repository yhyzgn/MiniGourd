package com.yhy.server.gourd.request;

import com.yhy.server.gourd.utils.ArrayUtils;
import com.yhy.server.gourd.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 9:31
 * version: 1.0.0
 * desc   :
 */
public class Request {
    private InputStream inputStream;

    private String url;
    private String method;
    private String version;

    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;

        String request = null;
        byte[] buffer = new byte[8 * 1024];
        int len = 0;
        if ((len = inputStream.read(buffer)) > 0) {
            request = new String(buffer, 0, len);
        }

        if (StringUtils.isNotEmpty(request)) {
            String[] headers = request.split("\n");
            if (ArrayUtils.isNotEmpty(headers)) {
                String[] generals = headers[0].split("\\s");
                url = generals[1];
                method = generals[0];
                version = generals[2];
            }
        }
    }

    public void close() throws IOException {
        if (null != inputStream) {
            inputStream.close();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
