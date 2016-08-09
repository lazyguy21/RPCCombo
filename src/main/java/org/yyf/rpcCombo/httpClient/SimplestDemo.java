package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.yyf.rpcCombo.cxf.domain.User;

import java.io.IOException;

/**
 * Created by tobi on 16-8-9.
 */
public class SimplestDemo {
    public static void main(String[] args) {
        CloseableHttpClient hc = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8888/test/user");
        try (CloseableHttpResponse response = hc.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            User user = JSON.parseObject(entity.getContent(), User.class);//利用fastjson转换inputstream流api直接转成javabean
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
