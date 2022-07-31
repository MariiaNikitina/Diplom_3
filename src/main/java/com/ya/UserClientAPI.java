package com.ya;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClientAPI extends BurgersClientAPI {
    private static final String USER_PATH = "api/auth/";

    @Step("Login with credentials{credentials}")
    public ValidatableResponse login(UserCredentialsAPI credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "login")
                .then();
    }

    @Step("Get user accessToken via credentials{credentials}")
    public String getUserAccessToken(String userEmail, String userPassword) {
        ValidatableResponse loginResponse = login(new UserCredentialsAPI(userEmail, userPassword));
        return loginResponse.extract().path("accessToken");
    }

    @Step("Delete user with credentials with credentials{credentials}")
    public ValidatableResponse deleteUser(String userEmail, String userPassword) {
        String accessToken = getUserAccessToken(userEmail, userPassword);
        return given()
                .spec(getBaseSpec())
                .body(accessToken)
                .when()
                .delete(USER_PATH + "user")
                .then();
    }

    @Step("Create user {user}")
    public ValidatableResponse create(UserAPI user) {
        String registerRequestBody = "{\"email\":\"" + user.getLogin() + "\","
                + "\"password\":\"" + user.getPassword() + "\","
                + "\"name\":\"" + user.getName() + "\"}";
        return given()
                .spec(getBaseSpec())
                .body(registerRequestBody)
                .when()
                .post(USER_PATH + "register")
                .then();
    }
}
