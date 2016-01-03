package org.journerist.wildfly.tokenBasedAuthentification.bean;

import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthAccessElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthLoginElement;

import java.util.Set;

public interface AuthService {
    AuthAccessElement login(AuthLoginElement loginElement);

    boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed);
}
