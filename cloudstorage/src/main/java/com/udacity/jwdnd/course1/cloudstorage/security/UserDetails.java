package com.udacity.jwdnd.course1.cloudstorage.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetails {
    /**
    Example retrieved from the internet on 19.11.2023
    Adapted Bihege Yannick on the same day
     */
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
