package org.yyf.rpcCombo.httpClient;

import org.yyf.rpcCombo.cxf.domain.Color;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobi on 16-8-10.
 */
public class HttpClientUtilTest {
    public static void main(String[] args) {
        String url = "http://localhost:8888/test/echoUser";
        Map mockUser = new HashMap<>();
        mockUser.put("id", 11L);
        mockUser.put("name", "王小明");
        mockUser.put("age", 1);
        mockUser.put("color", Color.BLUE.name());
        mockUser.put("date", new Date().getTime());
        mockUser.put("isGood", false);

//        user.setId(11L);
//        user.setName("小明明");
//        user.setAge(10);
//        user.setColor(Color.BLUE);
//        user.setDate(new Date());
//        user.setIsGood(false);
//        user.setMaps(null);
//        user.setLists(new ArrayList<>());

        Map map = HttpClientUtil.singleton.postInJsonReturnInMap(url, mockUser);
        System.out.println(map);

    }
}
