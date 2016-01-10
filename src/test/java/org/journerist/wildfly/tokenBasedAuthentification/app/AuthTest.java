package org.journerist.wildfly.tokenBasedAuthentification.app;


import org.journerist.wildfly.tokenBasedAuthentification.rest.Auth;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Method;

public class AuthTest {

    @Test
    public void login_hasLoginMethod() throws Exception {
        Assert.assertEquals(methodHasPostAnnotation("login"), true);
    }

    @Test
    public void login_matchesLoginRestUri() throws Exception {
        Assert.assertEquals(getPathForMethod("login"), "login");
    }

    @Test
    public void login_permitsAll() throws Exception {
        Assert.assertEquals(methodHasPermitAllAnnoation("login"), true);
    }

    @Test
    public void login_producesJson() throws Exception {
        Assert.assertEquals(getProducesForMethod("login")[0], MediaType.APPLICATION_JSON);
    }

    private String[] getProducesForMethod(String methodName) {
        Method[] declaredMethods = Auth.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals(methodName) && method.isAnnotationPresent(javax.ws.rs.Produces.class)) {
                Produces annotation = method.getAnnotation(Produces.class);
                return annotation.value();
            }
        }
        return null;
    }

    private boolean methodHasPermitAllAnnoation(String methodName) {
        Method[] declaredMethods = Auth.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals(methodName) && method.isAnnotationPresent(javax.annotation.security.PermitAll.class)) {
                return true;
            }
        }
        return false;
    }


    private String getPathForMethod(String methodName) {
        Method[] declaredMethods = Auth.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals(methodName) && method.isAnnotationPresent(javax.ws.rs.Path.class)) {
                Path annotation = method.getAnnotation(Path.class);
                return annotation.value();
            }
        }
        return null;
    }

    private boolean methodHasPostAnnotation(String methodName) {
        Method[] declaredMethods = Auth.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals(methodName) && method.isAnnotationPresent(javax.ws.rs.POST.class)) {
                return true;
            }
        }
        return false;
    }
}