package manuelgonzalo.zooplustest.account.service.impl;

import manuelgonzalo.zooplustest.account.model.Role;
import manuelgonzalo.zooplustest.account.model.User;
import manuelgonzalo.zooplustest.account.repository.RoleRepository;
import manuelgonzalo.zooplustest.account.repository.UserRepository;
import manuelgonzalo.zooplustest.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by manuel on 24/6/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(roleRepository.findByRole("USER"));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
