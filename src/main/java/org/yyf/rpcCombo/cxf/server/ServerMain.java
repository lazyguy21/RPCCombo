package org.yyf.rpcCombo.cxf.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.yyf.rpcCombo.cxf.api.TestService;

/**
 * Created by tobi on 16-8-1.
 */
public class ServerMain {
    public static void main(String[] args) {
//        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
////        sf.setResourceClasses(.class);
//        sf.setResourceProvider(TestServiceImpl.class, new SingletonResourceProvider(new TestServiceImpl()));
//        sf.setAddress("http://localhost:9000/");
//        sf.create();

        JAXRSServerFactoryBean rsFactory = new JAXRSServerFactoryBean();

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        jacksonJsonProvider.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        rsFactory.setProvider(jacksonJsonProvider);
        rsFactory.setAddress("http://localhost:8888");
        rsFactory.setResourceClasses(TestServiceImpl.class,ExceptionServiceImpl.class);
        rsFactory.create();
    }
}
