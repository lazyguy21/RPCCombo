package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
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
 */
public class PostDemo {
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

    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String userString = JSON.toJSONString(user);
            //这里注意设置了content-type=application/json;utf-8
            StringEntity stringEntity = new StringEntity(userString, ContentType.APPLICATION_JSON);
            HttpPost httpPost = new HttpPost("http://localhost:8888/test/echoUser");
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

            User user = httpClient.execute(httpPost, responseHandler);
            System.out.println(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
