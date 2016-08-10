package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tobi on 16-8-10.
 * 使用httpclient调用url的单例工具类
 * 依赖httpclient:4.5.2+fastjson1.2.15+jdk1.8
 */
public enum  HttpClientUtil {
    singleton;
    private final CloseableHttpClient httpClient;
//    PoolingHttpClientConnectionManager中最大连接数
    private final Integer MAX_TOTAL=50;
//    每一HttpRoute(即域名)下同时可以并发的请求数，如果应用中只请求一个域名，设置为连接池最大数即可，不设置默认是2
    private final Integer DEFAULT_MAX_PER_ROUTE = MAX_TOTAL;
//    从连接池中获取连接的超时时间，超过该时间未拿到可用连接，丢ConnectionPoolTimeoutException
    private final Integer CONNECTION_REQUEST_TIMEOUT = 5 * 1000;
//    连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
    private final Integer CONNECT_TIMEOUT = 5 * 1000;
//    服务器返回数据(response)的时间，超过该时间抛出read timeout
    private final Integer SOCKET_TIMEOUT = 5 * 1000;

    HttpClientUtil() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(MAX_TOTAL);
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                                .setConnectTimeout(CONNECT_TIMEOUT)
                                .setSocketTimeout(SOCKET_TIMEOUT)
                                .build())
                .setConnectionManager(cm)
                .build();
        this.httpClient = httpClient;
    }

    public CloseableHttpClient getHttpClient(){
        return httpClient;
    }

    /**
     * 执行请求，返回结果处理由ResponseHandler决定，参照MapResponseHandler用法
     * @param request
     * @param responseHandler
     * @param <T>
     * @return
     */
    public <T> T execute(final HttpUriRequest request,final ResponseHandler<? extends T> responseHandler) {
        try {
            T result = httpClient.execute(request, responseHandler);
            return result;
        } catch (IOException e) {
            throw new RuntimeException("error occurs when request a url",e);
        }
    }

    /**
     * 执行请求，将返回的结果流利用fastjson(其默认编码格式为utf-8)反序列化为Map,输出
     * @param request
     * @return
     */
    public Map executeReturnMap(final HttpUriRequest request) {
        return execute(request, new MapResponseHandler());
    }

    /**
     *执行请求，将返回的结果流(其默认编码格式为utf-8)用String输出
     * @param request
     * @return
     */
    public String executeReturnString(final HttpUriRequest request){
        return execute(request, response -> {
            checkResponse(response);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        });

    }

    public Map postInJsonReturnInMap(final String url,final Map parameterMap){
        String userString = JSON.toJSONString(parameterMap);//默认编码utf-8
        //这里注意设置了content-type=application/json;utf-8
        StringEntity stringEntity = new StringEntity(userString, ContentType.APPLICATION_JSON);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(stringEntity);

        return executeReturnMap(httpPost);

    }


    private class MapResponseHandler implements ResponseHandler<Map> {
        @Override
        public Map handleResponse(HttpResponse response) throws IOException {
            checkResponse(response);
            Map map = JSONObject.parseObject(response.getEntity().getContent(), Map.class);
            return map;
        }
    }

    /**
     * 校验response
     * @param response
     * @throws ClientProtocolException
     */
    private void checkResponse(HttpResponse response) throws ClientProtocolException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }
    }


}
