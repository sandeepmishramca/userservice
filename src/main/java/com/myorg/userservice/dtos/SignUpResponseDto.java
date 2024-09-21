package com.myorg.userservice.dtos;

import com.myorg.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpResponseDto {
    private User user;
    // TODO: send only required info not all info related to user
}
