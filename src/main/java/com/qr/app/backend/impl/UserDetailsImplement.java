package com.qr.app.backend.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qr.app.backend.models.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImplement implements UserDetails {
    private static final long serialVersionUID = -1352733651057286866L;
    private Admin admin;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(admin.getRole()));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return admin.getName();
    }
    @JsonIgnore
    public String getEmail(){
        return admin.getEmail();
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
