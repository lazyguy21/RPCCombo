package org.yyf.rpcCombo.serialize.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import static org.yyf.rpcCombo.domain.Color.RED;

/**
 * Created by tobi on 16-9-13.
 */
public class SimpleTest {
    public static void main(String[] args) {

        String s = JSON.toJSONString(RED, SerializerFeature.WriteEnumUsingToString);
        System.out.println(s);
    }
}
