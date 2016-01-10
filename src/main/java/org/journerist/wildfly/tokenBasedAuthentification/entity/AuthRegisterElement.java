package org.journerist.wildfly.tokenBasedAuthentification.entity;

import javax.ws.rs.FormParam;

public class AuthRegisterElement {

    @FormParam("name")
    public String name;

    @FormParam("password")
    public String password;

    @FormParam("email")
    public String email;

}