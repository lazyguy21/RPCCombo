package org.yyf.rpcCombo.serialize.fastJson;

import com.google.common.reflect.Invokable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import org.yyf.rpcCombo.domain.Person;
import org.yyf.rpcCombo.domain.Smell;

import static org.yyf.rpcCombo.domain.Color.BLUE;
import static org.yyf.rpcCombo.domain.Color.RED;

/**
 * Created by tobi on 16-9-13.
 */
public class SimpleTest {
    public static void main(String[] args) {
//        ContextValueFilter contextValueFilter = new ContextValueFilter() {
//            @Override
//            public Object process(BeanContext context, Object object, String name, Object value) {
////                System.out.println(context);
////                System.out.println(object);
////                System.out.println(name);
////                System.out.println(value);
////                System.out.println("hahda");
//                if(name.equals("color")){
//                    return BLUE;
//                }
//
//
//                return value;
//            }
//        };

//        AfterFilter afterFilter = new AfterFilter() {
//
//            @Override
//            public void writeAfter(Object object) {
//                System.out.println(object.getClass());
//                System.out.println("haha");
//                System.out.println(object);
//            }
//        };
        Person person = new Person();
        person.setColor(RED);
        person.setId(1L);
        person.setName("adfadf");
        person.setNormalSerializeColor(BLUE);
        person.setSmell(Smell.BAD);
//        String jsonString = JSON.toJSONString(person, contextValueFilter);
//        String jsonString = JSON.toJSONString(person, afterFilter);
        String jsonString = JSON.toJSONString(person);

        System.out.println(jsonString);

//        String s = JSON.toJSONString(RED, SerializerFeature.WriteEnumUsingToString);
//        System.out.println(s);
    }
}
