package com.myorg.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogoutRequestDto {
    private String token;
}
