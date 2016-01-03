package org.journerist.wildfly.tokenBasedAuthentification.entity;

import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class AuthLoginElement {

    @FormParam("username")
    private String username;

    @FormParam("password")
    private String password;


    public AuthLoginElement() {

    }

    public AuthLoginElement(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}