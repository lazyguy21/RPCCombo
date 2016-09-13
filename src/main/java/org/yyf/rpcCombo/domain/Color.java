package org.yyf.rpcCombo.domain;

/**
 * Created by tobi on 16-9-13.
 */
public enum Color implements ChineseLabelEnum {
    RED("红"),
    GREEN("绿"),
    BLUE("蓝");

    private String chineseLabel;

    Color(String chineseLabel) {
        this.chineseLabel = chineseLabel;
    }

    @Override
    public String getChineseLabel() {
        return chineseLabel;
    }
    @Override
    public String toString(){
        return chineseLabel;
    }

}
