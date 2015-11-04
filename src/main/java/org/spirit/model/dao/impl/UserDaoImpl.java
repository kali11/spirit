package org.spirit.model.dao.impl;

import org.spirit.model.dao.UserDao;
import org.spirit.model.dao.generic.LmsGenericDaoImpl;
import org.spirit.model.entity.User;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

@Repository
public class UserDaoImpl extends LmsGenericDaoImpl<User, Long> implements UserDao {

    @Override
    public User getByLogin(String login) {
        Search search = new Search(User.class).addFilterEqual("login", login);
        User user = this.searchUnique(search);
        return user;
    }

}
