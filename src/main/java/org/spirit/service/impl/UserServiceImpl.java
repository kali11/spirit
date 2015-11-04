package org.spirit.service.impl;

import java.util.List;

import org.spirit.model.dao.RoleDao;
import org.spirit.model.dao.UserDao;
import org.spirit.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.spirit.model.entity.Role;
import org.spirit.service.UserService;

/**
 *
 * @author Piotr Kalinowski
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void saveUser(User user, String roleId) {
        Role role = roleDao.find(Long.parseLong(roleId));
        user.setRole(role);
        this.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userDao.find(id);
    }

    @Override
    public void deleteUser(User user) {
        userDao.remove(user);
    }

    @Override
    public User get(String login) {
        return userDao.getByLogin(login);
    }

}
