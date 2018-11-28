package net.cloudcentrik.common.restclient;

import org.apache.http.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class RestClient {

    public static void build(String user,String password) throws Exception{

        // create custom http headers for httpclient
        List<BasicHeader> defaultHeaders = Arrays.asList(
                new BasicHeader("X-Tenant","bndfbn"),
                new BasicHeader("X-ConnectionId", "fghf")
        );

        RequestConfig requestConfig = RequestConfig.custom()
                .setAuthenticationEnabled(true)
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultHeaders(defaultHeaders)
                .addInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

                        System.out.println(httpRequest.getRequestLine().getMethod()+" "+
                                httpContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST)+httpRequest.getRequestLine().getUri());
                    }
                })
                .addInterceptorLast(new HttpResponseInterceptor(){
                    @Override
                    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                        System.out.println(httpResponse.getStatusLine().getReasonPhrase());
                        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
                    }

                })
                .build();

        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("api.dev.plugboard.io/api")
                .setPath("product")
                .setParameter("updateOnRemoteId","true")
                .build();

        HttpUriRequest request = RequestBuilder.get().setUri(uri)
        .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.ACCEPT,"application/json")
                .setHeader("Authorization", parseBasicAuthorizationHeader(user,password))
        .build();

        CloseableHttpResponse response =httpClient.execute(request);
        //System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public static String parseBasicAuthorizationHeader(String user,String password) throws Exception{

        byte[] bytes=new String(user+":"+password).getBytes("UTF-8");

        return "Basic " + Base64.getEncoder().encodeToString(bytes);
    }

}
