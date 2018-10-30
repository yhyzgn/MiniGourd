package com.yhy.server.gourd.response;

import com.yhy.server.gourd.request.Request;

import java.io.IOException;
import java.io.OutputStream;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 9:56
 * version: 1.0.0
 * desc   :
 */
public class Response {

    private Request request;
    private OutputStream outputStream;

    public Response(Request request, OutputStream outputStream) {
        this.request = request;
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb
                .append(request.getVersion()).append("200 OK").append(System.lineSeparator())
                .append("Content-Type: text/html").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("<!doctype html>")
                .append("<html>")
                .append("<head>")
                .append("<meta charset=\"utf-8\">")
                .append("</head>")
                .append("<body>")
                .append(content)
                .append("</body>")
                .append("</html>");

        outputStream.write(sb.toString().getBytes());
    }

    public void close() throws IOException {
        if (null != outputStream) {
            outputStream.close();
        }
    }
}
