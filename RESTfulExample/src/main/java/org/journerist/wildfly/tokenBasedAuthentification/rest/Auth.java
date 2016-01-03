package org.journerist.wildfly.tokenBasedAuthentification.rest;

import org.journerist.wildfly.tokenBasedAuthentification.bean.AuthService;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthAccessElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthLoginElement;
import org.jboss.resteasy.annotations.Form;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/RESTfulExample/rest/message/hello%20world
@Path("/auth")
public class Auth {

	@EJB
	AuthService authService;

	@POST
	@Path("login")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public AuthAccessElement login(@Context HttpServletRequest request, @Form AuthLoginElement loginElement) {
		AuthAccessElement accessElement = authService.login(loginElement);
		if (accessElement != null) {
			request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_ID, accessElement.getAuthId());
			request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, accessElement.getAuthToken());
		}
		return accessElement;

	}

	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("user")
	public AuthAccessElement test() {
		return new AuthAccessElement("A", "B", "C");

	}
}