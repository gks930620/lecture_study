package com.study.password;

public class WrongCorrectFixedPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "aa";  //2글자
    }
}
