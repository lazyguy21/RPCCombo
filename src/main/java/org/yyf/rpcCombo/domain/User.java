package org.yyf.rpcCombo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class User {

    private Long   id;
    private String name;
//    @JSONField(format = "YYYY-MM-DD HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private LocalDate date;
    @JSONField(format = "HH:mm:ss")
    private LocalTime time;

}