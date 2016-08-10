package org.yyf.rpcCombo.cxf.server;

import org.apache.logging.log4j.core.util.Throwables;
import org.yyf.rpcCombo.cxf.api.TestService;
import org.yyf.rpcCombo.cxf.domain.Color;
import org.yyf.rpcCombo.cxf.domain.CustomResponse;
import org.yyf.rpcCombo.cxf.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Path;

/**
 * Created by tobi on 16-8-1.
 */
public class TestServiceImpl implements TestService{
    @Override
    public String echo() {
        System.out.println("hello");
        return "hehe";
    }

    @Override
    public User echo2() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setId(11L);
        user.setName("小明明");
        user.setAge(10);
        user.setColor(Color.BLUE);
        user.setDate(new Date());
        user.setIsGood(false);
        user.setMaps(null);
        user.setLists(new ArrayList<>());
        System.out.println(user+" "+Thread.currentThread().getId());
        return user;
    }

    @Override
    public User echoUser(User user) {

        return user;

    }

    @Override
    public User timeEchoUser(User user) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;

    }
}
