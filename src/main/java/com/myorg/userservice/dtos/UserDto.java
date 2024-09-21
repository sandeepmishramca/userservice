package com.myorg.userservice.dtos;

import com.myorg.userservice.models.Role;
import com.myorg.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserDto fromUser(User user) {
        if(user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
        //we are not sending password to FE we can control here
    }
}
