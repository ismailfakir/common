package net.cloudcentrik.common.restclient.http;

import org.apache.http.Header;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Http {

    private String url;
    private List<String> path;
    private Map<String, String> query;
    private List<Header> headers;
    private ContentType contentType;
    private boolean isDebugEnabled = false;
    private RequestConfig requestConfig;
    private PoolingHttpClientConnectionManager connectionManager;
    private SSLConnectionSocketFactory sslConnectionSocketFactory;
    private HttpRequestInterceptor httpRequestInterceptor;
    private HttpResponseInterceptor httpResponseInterceptor;
    private ResponseHandler responseHandler;

    private Http(
            String url, List<String> path, Map<String, String> query, List<Header> headers, ContentType contentType,
            boolean isDebugEnabled, RequestConfig requestConfig, PoolingHttpClientConnectionManager connectionManager,
            SSLConnectionSocketFactory sslConnectionSocketFactory, HttpRequestInterceptor httpRequestInterceptor,
            HttpResponseInterceptor httpResponseInterceptor, ResponseHandler responseHandler) {
        this.url = url;
        this.path = path;
        this.query = query;
        this.headers = headers;
        this.contentType = contentType;
        this.isDebugEnabled = isDebugEnabled;
        this.requestConfig = requestConfig;
        this.connectionManager = connectionManager;
        this.sslConnectionSocketFactory = sslConnectionSocketFactory;
        this.httpRequestInterceptor = httpRequestInterceptor;
        this.httpResponseInterceptor = httpResponseInterceptor;
        this.responseHandler = responseHandler;
    }

    public static Http Http(final String url) {
        return Http(http -> http.url(url));
    }

    public static Http Http(final Consumer<Builder> builderConsumer) {
        final Builder builder = Builder.builder();
        builderConsumer.accept(builder);
        return builder.build();
    }

    public static class Builder {

        String url;
        List<String> path;
        Map<String, String> query;
        List<Header> headers;
        ContentType contentType;
        boolean isDebugEnabled = false;
        RequestConfig requestConfig;
        PoolingHttpClientConnectionManager connectionManager;
        SSLConnectionSocketFactory sslConnectionSocketFactory;
        HttpRequestInterceptor httpRequestInterceptor;
        HttpResponseInterceptor httpResponseInterceptor;
        ResponseHandler responseHandler;

        private Builder() { }

        private Builder(
                String url, List<String> path, Map<String, String> query, List<Header> headers, ContentType contentType,
                boolean isDebugEnabled, RequestConfig requestConfig, PoolingHttpClientConnectionManager connectionManager,
                SSLConnectionSocketFactory sslConnectionSocketFactory, HttpRequestInterceptor httpRequestInterceptor,
                HttpResponseInterceptor httpResponseInterceptor, ResponseHandler responseHandler) {
            this.url = url;
            this.path = path;
            this.query = query;
            this.headers = headers;
            this.contentType = contentType;
            this.isDebugEnabled = isDebugEnabled;
            this.requestConfig = requestConfig;
            this.connectionManager = connectionManager;
            this.sslConnectionSocketFactory = sslConnectionSocketFactory;
            this.httpRequestInterceptor = httpRequestInterceptor;
            this.httpResponseInterceptor = httpResponseInterceptor;
            this.responseHandler = responseHandler;
        }

        protected static Builder builder() {
            return new Builder();
        }

        protected static Builder Builder(Http http) {
            return new Builder(http.url, http.path, http.query, http.headers, http.contentType,
                    http.isDebugEnabled, http.requestConfig, http.connectionManager, http.sslConnectionSocketFactory,
                    http.httpRequestInterceptor,http.httpResponseInterceptor,http.responseHandler);
        }

        protected Http build() {
            return new Http(url, path, query, headers, contentType, isDebugEnabled,
                    requestConfig, connectionManager, sslConnectionSocketFactory,
                    httpRequestInterceptor,httpResponseInterceptor,responseHandler);
        }

        public Builder url(final String url) {
            this.url = url;
            return this;
        }

        public Builder path(final List<String> path) {
            this.path = path;
            return this;
        }

        public Builder query(final Map<String, String> queryParams) {
            this.query = queryParams;
            return this;
        }

        public Builder headers(final List<Header> headers) {
            this.headers = headers;
            return this;
        }

        public Builder contentType(final ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder debugEnabled(final boolean debugEnabled) {
            this.isDebugEnabled = debugEnabled;
            return this;
        }

        public Builder requestConfig(final RequestConfig requestConfig) {
            this.requestConfig = requestConfig;
            return this;
        }

        public Builder connectionManager(final PoolingHttpClientConnectionManager connectionManager) {
            this.connectionManager = connectionManager;
            return this;
        }

        public Builder sslConnectionSocketFactory(final SSLConnectionSocketFactory sslConnectionSocketFactory) {
            this.sslConnectionSocketFactory = sslConnectionSocketFactory;
            return this;
        }

        public Builder httpRequestInterceptor(final HttpRequestInterceptor httpRequestInterceptor) {
            this.httpRequestInterceptor = httpRequestInterceptor;
            return this;
        }

        public Builder httpResponseInterceptor(final HttpResponseInterceptor httpResponseInterceptor) {
            this.httpResponseInterceptor = httpResponseInterceptor;
            return this;
        }

        public Builder responseHandler(final ResponseHandler responseHandler) {
            this.responseHandler = responseHandler;
            return this;
        }


    }
}
