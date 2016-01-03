package org.journerist.wildfly.tokenBasedAuthentification.bean;

import org.journerist.wildfly.tokenBasedAuthentification.entity.User;

import javax.ejb.Stateless;

@Stateless
public class UserBean implements UserService{

    public User findByUsernameAndPassword(String name, String password) {
        User user = new User();
        user.setAuthRole("guest");
        return user;
    }

    public void save(User user) {

    }

    @Override
    public User findByUsernameAndAuthToken(String authId, String authToken) {
        User user = new User();
        user.setAuthRole("guest");
        return user;
    }
}
