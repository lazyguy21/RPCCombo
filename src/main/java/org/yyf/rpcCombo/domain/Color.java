package org.yyf.rpcCombo.domain;

import org.yyf.rpcCombo.serialize.fastJson.EnumDescription;

/**
 * Created by tobi on 16-9-13.
 */
public enum Color implements EnumDescription {
    RED("红"),
    GREEN("绿"),
    BLUE("蓝");

    private String description;

    Color(String description) {
        this.description = description;
    }


    @Override
    public String toString(){
        return description;
    }


    @Override
    public String getDescription() {
        return description;
    }
}
