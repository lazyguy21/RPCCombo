package org.yyf.rpcCombo.cxf.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

/**
 * Created by tobi on 16-8-2.
 */
@Data
public class User {
    private String name;
    private Integer age;
    private Long id;
    private Date date;
    private Color color;
    private Boolean isGood;

    private List<String> lists;

    private Map<String,String> maps;

}
