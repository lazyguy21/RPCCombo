package org.yyf.rpcCombo.domain;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-9-29.
 */
public class ManyObject {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(1000L * 10);
            Byte[] TenMArray = new Byte[1024 * 1024 * 10];
        }
//        TimeUnit.DAYS.sleep(10L);
    }
}
