package com.myorg.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity(name = "users")
public class User extends BaseModel{
    private String name;
    private String hashedPassword;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
