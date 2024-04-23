package com.shanks.response;


import com.shanks.model.ROLE;
import lombok.Data;

@Data

public class AuthResponse {

    private String jwt;
    private String message;
    private ROLE USERRole;
}
