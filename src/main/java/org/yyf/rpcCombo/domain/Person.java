package org.yyf.rpcCombo.domain;

import com.alibaba.fastjson.annotation.JSONField;

import org.yyf.rpcCombo.serialize.fastJson.EnumDescriptionSerializer;

import lombok.Data;

/**
 * Created by tobi on 16-9-27.
 */
@Data
public class Person {

    @JSONField(serializeUsing = EnumDescriptionSerializer.class)
    private Color color;
    private Long id;
    private String name;
    private Color normalSerializeColor;

    @JSONField(serializeUsing = EnumDescriptionSerializer.class)
    private Smell smell;

}
