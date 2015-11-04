package org.spirit.model.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import org.spirit.model.entity.User;

public interface UserDao extends GenericDAO<User, Long> {

    public User getByLogin(String login);

}
