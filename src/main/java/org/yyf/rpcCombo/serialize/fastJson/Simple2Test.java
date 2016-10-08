package org.yyf.rpcCombo.serialize.fastJson;

import com.alibaba.fastjson.JSON;

import org.yyf.rpcCombo.domain.Person;
import org.yyf.rpcCombo.domain.Smell;

import static org.yyf.rpcCombo.domain.Color.BLUE;
import static org.yyf.rpcCombo.domain.Color.RED;

/**
 * Created by tobi on 16-9-13.
 */
public class Simple2Test {
    public static void main(String[] args) {

        Person person = new Person();
        person.setColor(RED);
        person.setId(1L);
        person.setName("adfadf");
        person.setNormalSerializeColor(BLUE);
        person.setSmell(Smell.BAD);

        String jsonString = JSON.toJSONString(person);

        System.out.println(jsonString);

//        String s = JSON.toJSONString(RED, SerializerFeature.WriteEnumUsingToString);
//        System.out.println(s);
    }
}
