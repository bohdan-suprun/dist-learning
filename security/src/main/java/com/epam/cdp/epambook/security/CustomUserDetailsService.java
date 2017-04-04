package com.epam.cdp.epambook.security;

import com.epam.cdp.epambook.model.User;
import com.epam.cdp.epambook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Bohdan_Suprun
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with name %s not found", username));
        }
        return new MyUserDetails(user);
    }

    private static final class MyUserDetails implements UserDetails {
        private static final SimpleGrantedAuthority DEFAULT_GRANTED_AUTHORITY = new SimpleGrantedAuthority("ROLE_USER");
        private final User user;


        private MyUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singleton(DEFAULT_GRANTED_AUTHORITY);
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
