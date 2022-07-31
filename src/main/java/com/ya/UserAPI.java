package com.ya;

import lombok.Data;

@Data
public class UserAPI {
    private String login;
    private String password;
    private String name;

    public UserAPI(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public UserAPI() {
    }
}
