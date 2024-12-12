package com.conexemi.emi.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    String email;
    String userPassword;

}
