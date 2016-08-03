package org.yyf.rpcCombo.httpClient;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tobi on 16-7-11.
 */
public class Whatever {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (true){
            executorService.submit(() -> {
                Content content = null;
                try {
                    content = Request.Get("http://localhost:8080/test/test/test1")
                            .execute().returnContent();
                    System.out.println(content.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
