package org.yyf.rpcCombo.cxf.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by tobi on 16-8-3.
 */
@Path("/exception")
public interface ExceptionService {
    @Path("/ex")
    @GET
    void ex();

    @Path("/ex2")
    @GET
    List ex2();

    @Path("/ex3")
    @GET
    Exception ex3();

    @Path("/ex4")
    @GET
    String ex4();
}
