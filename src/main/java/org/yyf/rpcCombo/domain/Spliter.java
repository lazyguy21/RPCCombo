package org.yyf.rpcCombo.domain;

import java.util.Arrays;

/**
 * Created by tobi on 16-9-28.
 */
public class Spliter {
    public static void main(String[] args) {
        String[] split = "asdf|asdf|asdf".split("\\|");
        System.out.println(Arrays.toString(split));
    }
}
