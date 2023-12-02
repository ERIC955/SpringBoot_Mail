package com.eric.springbootmall.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;


@Data
public class UserRegisterRequest {


    @NonNull
    @Email
    private String email;
    @NonNull
    private String password;
}
