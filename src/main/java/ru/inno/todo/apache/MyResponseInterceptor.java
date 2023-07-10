package ru.inno.todo.apache;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyResponseInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        System.out.println(httpResponse.getStatusLine());
        for (Header header : httpResponse.getAllHeaders()) {
            System.out.println(header);
        }

//        CloseableHttpResponse req = (CloseableHttpResponse) httpContext.getAttribute("http.response");
//        System.out.println("BODY: ===>");
//        if (req.getEntity() == null) {
//            System.out.println("NO BODY");
//        } else {
//            System.out.println(EntityUtils.toString(req.getEntity()));
//        }


        // Можем модифицировать ответ
//        httpResponse.removeHeaders("Content-Length"); //2
//        httpResponse.addHeader("Content-Length", "0");

    }
}
