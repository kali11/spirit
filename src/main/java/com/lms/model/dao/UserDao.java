package com.lms.model.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.lms.model.entity.User;

public interface UserDao extends GenericDAO<User, Long> {

    public User getByLogin(String login);

}
