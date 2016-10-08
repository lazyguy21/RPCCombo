package org.yyf.rpcCombo.serialize.fastJson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;


public class Enum2DescriptionSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                      int features) throws IOException {
//        if (object instanceof Enum) {
//            Enum enumObject = (Enum) object;
//            HashMap<Object, Object> map = new HashMap<>();
//            map.put("ordinal", enumObject.ordinal());
//            map.put("name", enumObject.name());
//            if (object instanceof EnumDescription) {
//                EnumDescription enumDescriptionObject = (EnumDescription) object;
//                map.put("description", enumDescriptionObject.getDescription());
//            }
//            serializer.write(map);
//        }
        serializer.write(object);


    }
}