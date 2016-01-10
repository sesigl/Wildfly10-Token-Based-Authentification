package org.journerist.wildfly.tokenBasedAuthentification.bean;

import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthAccessElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthLoginElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthRegisterElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        user = userService.findByUsernameAndPassword(loginElement.getName(), loginElement.getPassword());
        if (user != null) {
            user.setAuthToken(UUID.randomUUID().toString());
            userService.save(user);
            return new AuthAccessElement(loginElement.getName(), user.getAuthToken(), user.getRole());
        }
        return null;
    }

    @Override
    public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed) {
        User user = userService.findByUsernameAndAuthToken(authId, authToken);
        return user != null && rolesAllowed.contains(user.getRole());
    }

    @Override
    public void register(AuthRegisterElement registerElement) {


        User user = new User();
        user.setPassword(registerElement.password);
        user.setEmail(registerElement.email);
        user.setName(registerElement.name);
        user.setRole("user");

        userService.register(user);

    }

}