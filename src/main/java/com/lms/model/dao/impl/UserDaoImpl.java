package com.lms.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.lms.model.dao.UserDao;
import com.lms.model.dao.generic.LmsGenericDaoImpl;
import com.lms.model.entity.User;

@Repository
public class UserDaoImpl extends LmsGenericDaoImpl<User, Long> implements UserDao {

    @Override
    public User getByLogin(String login) {
        Search search = new Search(User.class).addFilterEqual("login", login);
        User user = this.searchUnique(search);
        return user;
    }

}
