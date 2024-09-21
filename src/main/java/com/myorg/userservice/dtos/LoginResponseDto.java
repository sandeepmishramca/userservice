package com.myorg.userservice.dtos;

import com.myorg.userservice.models.Token;
import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
    private Token token;
}
