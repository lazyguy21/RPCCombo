package org.yyf.rpcCombo.serialize.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.yyf.rpcCombo.domain.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by tobi on 2017/1/10.
 */
public class NewTimeTest {
    @Test
    public void testLocalDate(){
        User user = new User();
        user.setId(1L);
        user.setName("hha");
        user.setCreateDate(LocalDateTime.now());
        user.setDate(LocalDate.now());
        user.setTime(LocalTime.now());
        String s = JSON.toJSONString(user);
        System.out.println(s);
        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now.);
        long epochSecond = Instant.now().getEpochSecond();
        System.out.println(epochSecond);
//        String s2 = "{\"date\":\"2017-01-10\",\"id\":1,\"name\":\"hha\",\"time\":\"17:27:55.805\"}";
        String s2 = "{\"createDate\":\"2017-01-10 17:28:28\",\"date\":\"2017-01-10\",\"id\":1,\"name\":\"hha\",\"time\":\"17:28:28.640\"}";
        User user1 = JSON.parseObject(s2, User.class);
        System.out.println(user1);

    }
    @Test
    public void testDate() {
        LocalDateTime now = LocalDateTime.now();
        String s = JSON.toJSONString(now);
        System.out.println(s);
    }

    @Test
    public void tett() throws Exception {
        Date date = new Date();
        String s = JSON.toJSONString(date);
        System.out.println(s);

    }


    @Test
    public void testFrom() throws Exception {
        LocalDateTime parse = LocalDateTime.parse("2017-01-10 17:28:28", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);

    }


    @Test
    public void testDate2() {
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond());
        String s = JSON.toJSONString(now);
        System.out.println(s);
    }
}
