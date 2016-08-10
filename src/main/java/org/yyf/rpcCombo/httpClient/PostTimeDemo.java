package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.yyf.rpcCombo.cxf.domain.Color;
import org.yyf.rpcCombo.cxf.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tobi on 16-8-9.
 * httpRoute是指域名，而默认的maxConnectionPerRoute=2,注意！
 */
public class PostTimeDemo {
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
        CloseableHttpClient httpClient = HttpClients.createDefault();
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
                        System.out.println(new Date()+" from 1 "+user);
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
                        User user1 = httpClient.execute(httpPostNoParam, responseHandler);
                        System.out.println(new Date() + " from 2 " + user1);
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
