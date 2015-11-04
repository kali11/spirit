package com.lms.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.dao.UserDao;
import com.lms.model.entity.Role;
import com.lms.service.UserSecurityService;

/**
 *
 * @author Piotr Kalinowski
 *
 */
@Service
@Transactional
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        com.lms.model.entity.User user = userDao.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    /**
     * com.lms.model.entity.User user to
     * org.springframework.security.core.userdetails.User
     *
     * @param user
     * @param authorities
     * @return
     */
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(
            com.lms.model.entity.User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                user.isActive(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }

}
