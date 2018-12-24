package net.cloudcentrik.common.restclient;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;

import java.io.IOException;

public class HttpRequestInterceptorImpl implements HttpRequestInterceptor {
    private boolean isDebugEnabled=false;

    public HttpRequestInterceptorImpl(boolean isDebugEnabled) {
        this.isDebugEnabled = isDebugEnabled;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

        if (isDebugEnabled) {
            System.out.println(httpRequest.getRequestLine().getMethod() + " " +
                    httpContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST) + httpRequest.getRequestLine().getUri());
        }
    }
}
