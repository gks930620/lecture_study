package com.study.password;

import com.study.password.PasswordGenerator;

public class CorrectFixedPasswordGenerator implements PasswordGenerator {


    @Override
    public String generatePassword() {
        return "aabbccdd";  //8글자
    }
}
