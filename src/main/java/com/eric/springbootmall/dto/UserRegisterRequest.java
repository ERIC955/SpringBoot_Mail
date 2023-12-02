package com.eric.springbootmall.dto;

import lombok.Data;
import lombok.NonNull;


@Data
public class UserRegisterRequest {


    @NonNull
    private String email;
    @NonNull
    private String password;
}
