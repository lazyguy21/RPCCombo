package org.yyf.rpcCombo.cxf.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.yyf.rpcCombo.cxf.api.TestService;
import org.yyf.rpcCombo.cxf.domain.Color;
import org.yyf.rpcCombo.cxf.domain.User;
import org.yyf.rpcCombo.cxf.server.TestServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by tobi on 16-8-1.
 */
public class ClientMain {
    public static void main(String[] args) {
//        Client client = ClientBuilder.newBuilder().newClient();
//        WebTarget target = client.target("http://localhost:9000/test/");
//        target = target.path("echo/");
//
//        Invocation.Builder builder = target.request();
//        Response response = builder.get();
//        TestService book = builder.get(TestService.class);
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        jacksonJsonProvider.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TestService testService = JAXRSClientFactory.create("http://localhost:8888", TestService.class, Arrays.asList(jacksonJsonProvider));
        String echo = testService.echo();
        System.out.println(echo);
        User user = testService.echo2();
        System.out.println(user);

        User userCandidate = new User();
        userCandidate.setId(11L);
        userCandidate.setName(null);
        userCandidate.setAge(10);
        userCandidate.setColor(Color.BLUE);
        userCandidate.setDate(new Date());
        userCandidate.setIsGood(false);
        userCandidate.setMaps(null);
        userCandidate.setLists(new ArrayList<>());
        testService.echoUser(userCandidate);
        System.out.println(userCandidate);

    }
}
