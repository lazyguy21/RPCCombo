package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.yyf.rpcCombo.cxf.domain.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tobi on 16-8-9.
 */
public class SimplestDemo {
    public static void main(String[] args) {
        CloseableHttpClient hc = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8888/test/user");
        try (CloseableHttpResponse response = hc.execute(httpGet)) {
            HttpEntity entity = response.getEntity();

            byteArrayToBean(entity);
//            getHandsWet(entity);
//            stringToBean(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getHandsWet(HttpEntity entity) throws IOException {
        //            这种方法需要自己手工关闭底层InputStream
        InputStream content = entity.getContent();
        User user = JSON.parseObject(content, User.class);//利用fastjson转换inputstream流api直接转成javabean
        content.close();
    }

    private static void byteArrayToBean(HttpEntity entity) throws IOException {
        byte[] bytes = EntityUtils.toByteArray(entity);
        User user = JSON.parseObject(bytes, User.class);
    }

    private static void stringToBean(HttpEntity entity) throws IOException {
        String s = EntityUtils.toString(entity,"utf-8");//底层源码是查找httpheader，方法设置，再没有就是ISO-8859-1
        User user = JSON.parseObject(s, User.class);
        System.out.println(user);
        System.out.println(s);
    }
}
