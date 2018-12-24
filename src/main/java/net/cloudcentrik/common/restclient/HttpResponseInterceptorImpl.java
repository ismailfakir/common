package net.cloudcentrik.common.restclient;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpResponseInterceptorImpl implements HttpResponseInterceptor {
    private boolean isDebugEnabled=false;

    public HttpResponseInterceptorImpl(boolean isDebugEnabled) {
        this.isDebugEnabled = isDebugEnabled;
    }

    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        if (isDebugEnabled) {
            System.out.println(httpResponse.getStatusLine().getReasonPhrase());
        }
    }
}
