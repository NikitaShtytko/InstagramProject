package com.netcracker.edu.fapi.security;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.models.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public final class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                getAuthority(user.getRole()),
                user.getFirstName(),
                user.getLastName()
        );
    }


    private static Set<SimpleGrantedAuthority> getAuthority(UserRole role) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return authorities;
    }
}
