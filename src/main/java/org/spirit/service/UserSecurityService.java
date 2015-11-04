package org.spirit.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserSecurityService extends UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException;

}
