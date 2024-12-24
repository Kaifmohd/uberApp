package com.sdekaif.project.uberbackend.uberApp.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
