package com.myorg.userservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.myorg.userservice.models.Role;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@Setter
@NoArgsConstructor //Jaction might be creating opject so I don't want to call my constructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority=role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
