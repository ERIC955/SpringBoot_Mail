package com.eric.springbootmall.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserloginRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
