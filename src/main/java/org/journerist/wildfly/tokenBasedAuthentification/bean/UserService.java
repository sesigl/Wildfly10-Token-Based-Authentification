package org.journerist.wildfly.tokenBasedAuthentification.bean;

import org.journerist.wildfly.tokenBasedAuthentification.entity.User;

public interface UserService {
    User findByUsernameAndPassword(String name, String password);

    void save(User user);

    User findByUsernameAndAuthToken(String authId, String authToken);
}
