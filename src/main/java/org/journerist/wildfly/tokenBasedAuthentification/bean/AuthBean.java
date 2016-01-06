package org.journerist.wildfly.tokenBasedAuthentification.bean;

import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthAccessElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthLoginElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Stateless
public class AuthBean implements AuthService {

    @EJB
    UserService userService;

    @PersistenceContext(unitName = "examplePU") // default type is PersistenceContextType.TRANSACTION
    EntityManager em;

    public AuthAccessElement login(AuthLoginElement loginElement) {

        User user;
        user = userService.findByUsernameAndPassword(loginElement.getUsername(), loginElement.getPassword());
        if (user != null) {
            user.setAuthToken(UUID.randomUUID().toString());
            userService.save(user);
            return new AuthAccessElement(loginElement.getUsername(), user.getAuthToken(), user.getRole());
        }
        return null;
    }

    @Override
    public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed) {
        User user = userService.findByUsernameAndAuthToken(authId, authToken);
        return user != null && rolesAllowed.contains(user.getRole());
    }

}