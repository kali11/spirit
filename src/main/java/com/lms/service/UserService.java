package com.lms.service;

import java.util.List;

import com.lms.model.entity.User;

public interface UserService {

    public void saveUser(User user);

    public void saveUser(User user, String roleId);

    public User getUser(Long id);

    public User get(String login);

    public List<User> getAllUsers();

    public void deleteUser(User user);

}
