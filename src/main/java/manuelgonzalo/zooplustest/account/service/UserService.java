package manuelgonzalo.zooplustest.account.service;

import manuelgonzalo.zooplustest.account.model.User;

/**
 * Created by manuel on 24/6/17.
 */
public interface UserService {
    void save(User user);

    User findByEmail(String email);
}
