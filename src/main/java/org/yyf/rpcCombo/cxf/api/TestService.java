package org.yyf.rpcCombo.cxf.api;

import org.yyf.rpcCombo.cxf.domain.CustomResponse;
import org.yyf.rpcCombo.cxf.domain.User;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by tobi on 16-8-1.
 */
@Path(value = "/test") //声明uri路径
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface TestService {
    @Path("/echo")
    @GET
    @Produces(value = MediaType.TEXT_PLAIN)
    String echo();

    @Path("/user")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    User echo2();

    @Path("/echoUser")
    @POST
    User echoUser(User user);
}
