package com.study.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("패스워드 초기화한다.")
    @Test
    void passwordTest() {
        //given
        User user= new User();
        //when
        user.initPassword(new CorrectFixedPasswordGenerator()); //항상 8글자.

        assertThat( user.getPassword()).isNotNull();

    }

    @DisplayName("패스워드가 요구사항에 부합되지 않아 초기화 되지 않는다.")
    @Test
    void passwordTest2() {
        //given
        User user= new User();
        //when
        user.initPassword(new WrongFixedPasswordGenerator());

        assertThat( user.getPassword()).isNull();

    }

}