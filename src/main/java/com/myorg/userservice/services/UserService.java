package com.myorg.userservice.services;

import com.myorg.userservice.models.Token;
import com.myorg.userservice.models.User;

public interface UserService {
    public Token login(String email, String password);
    public void logOut(String token);
    public User signUp(String name, String email, String password);
    public User validate(String token);//token has both info user id also if you have token
}
