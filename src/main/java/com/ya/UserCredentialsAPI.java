package com.ya;

import lombok.Data;

@Data
public class UserCredentialsAPI {
    private final String email;
    private final String password;

    public UserCredentialsAPI(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
