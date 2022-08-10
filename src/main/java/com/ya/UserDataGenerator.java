package com.ya;

import org.apache.commons.lang3.RandomStringUtils;

public class UserDataGenerator {
    public static UserAPI getRandomData() {
        String userLogin = (RandomStringUtils.randomAlphabetic(7) + "@mail.ru").toLowerCase();
        String userPassword = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        String userName = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        return new UserAPI(userLogin, userPassword, userName);
    }
}
