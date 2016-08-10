package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.yyf.rpcCombo.cxf.domain.Color;
import org.yyf.rpcCombo.cxf.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by tobi on 16-8-9. httpRoute是指域名，而默认的maxConnectionPerRoute=2,注意！
 */
public class MaxConnectionPerRouteTest {
    private static User user = new User();

    static {
        user.setId(11L);
        user.setName("小明明");
        user.setAge(10);
        user.setColor(Color.BLUE);
        user.setDate(new Date());
        user.setIsGood(false);
        user.setMaps(null);
        user.setLists(new ArrayList<>());
    }

    public static CloseableHttpClient buildHttpClient() {
//        CloseableHttpClient httpClient = HttpClients.createDefault();


        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
// Increase max total connection to 200
        cm.setMaxTotal(200);
// Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(1);
// Increase max connections for localhost:80 to 50
//        HttpHost localhost = new HttpHost("locahost", 80);
//        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

/*  请求参数配置
connectionRequestTimeout:
        *                从连接池中获取连接的超时时间，超过该时间未拿到可用连接，
                                会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
        connectTimeout:
        *                  连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
        *           socketTimeout:
        *                  服务器返回数据(response)的时间，超过该时间抛出read timeout*/

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(-1).setConnectTimeout(2000).setSocketTimeout(-1).build())
                .setConnectionManager(cm)
                .build();

        return httpClient;
    }

    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = buildHttpClient();
            String userString = JSON.toJSONString(user);
            //这里注意设置了content-type=application/json;utf-8
            StringEntity stringEntity = new StringEntity(userString, ContentType.APPLICATION_JSON);
            HttpPost httpPost = new HttpPost("http://localhost:8888/test/timeEchoUser");
            httpPost.setEntity(stringEntity);

            ResponseHandler<User> responseHandler = response -> {
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
                User user1 = JSON.parseObject(entity.getContent(), User.class);

                return user1;
            };


            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        User user = httpClient.execute(httpPost, responseHandler);
                        System.out.println(new Date() + " from 1 " + user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
                System.out.println(thread.getId() + " has start!");

            }

            System.out.println("begin time " + new Date());
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                Thread thread2 = new Thread(() -> {
                    String url = "http://localhost:8888/test/user";
                    HttpPost httpPostNoParam = new HttpPost(url);
                    try {
//                        User user1 = httpClient.execute(httpPostNoParam, responseHandler);
//                        System.out.println(new Date() + " from 2 " + user1);
                        Map map = httpClient.execute(httpPostNoParam, new MapResponseHandler());
                        System.out.println(map);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
                thread2.start();
                System.out.println(thread2.getId() + " has start22!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
