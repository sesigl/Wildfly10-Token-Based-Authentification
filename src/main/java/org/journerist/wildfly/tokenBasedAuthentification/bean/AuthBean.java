package org.journerist.wildfly.tokenBasedAuthentification.bean;

import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthAccessElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.AuthLoginElement;
import org.journerist.wildfly.tokenBasedAuthentification.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

@Stateless
public class AuthBean implements AuthService {

    @EJB
    UserService userService;

    @PersistenceContext(unitName = "examplePU") // default type is PersistenceContextType.TRANSACTION
    EntityManager em;

    public AuthAccessElement login(AuthLoginElement loginElement) {

        User u = new User();
        u.setName("testuser");
        em.persist(u);

        Query nativeQuery = em.createNativeQuery(
                "select count(*) from user"
        );
        BigInteger count = (BigInteger) nativeQuery.getSingleResult();

        User user;
        user = userService.findByUsernameAndPassword(loginElement.getUsername(), loginElement.getPassword());
        if (user != null) {
            user.setAuthToken(UUID.randomUUID().toString());
            userService.save(user);
            return new AuthAccessElement(loginElement.getUsername(), user.getAuthToken(), user.getAuthRole());
        }
        return null;
    }

    @Override
    public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed) {
        User user = userService.findByUsernameAndAuthToken(authId, authToken);
        return user != null && rolesAllowed.contains(user.getAuthRole());
    }
}