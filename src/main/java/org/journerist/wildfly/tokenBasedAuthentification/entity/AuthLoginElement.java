package org.journerist.wildfly.tokenBasedAuthentification.entity;

import javax.ws.rs.FormParam;

public class AuthLoginElement {

    @FormParam("name")
    private String name;

    @FormParam("password")
    private String password;


    public AuthLoginElement() {

    }

    public AuthLoginElement(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}