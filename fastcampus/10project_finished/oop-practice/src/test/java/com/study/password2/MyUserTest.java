package com.study.password2;

import com.study.password.User;
import com.study.password.WrongCorrectFixedPasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/**
 * 요구사항
 * • 비밀번호는 최소 8자 이상 12자 이하여야 한다.
 * • 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생
 * 시킨다.
 * • 경계조건에 대해 테스트 코드를 작성해야 한다
 *
 */

import static org.assertj.core.api.Assertions.assertThat;

public class MyUserTest {

    @DisplayName("패스워드초기화")
    @Test
    void passwordTest() {
        //given
        MyUser myUser = new MyUser();
        //when
        myUser.initPassword(new MyCorrectFixedPasswordGenerator());
        //then
        assertThat(myUser.getPassword()).isNotNull();
    }

    @DisplayName("패스워드 요구사항에 부합하지 않아 초기화가 되지 않는다")
    @Test
    void passwordTest2(){
        //given
        MyUser myuser = new MyUser();

        //when
        myuser.initPassword( new MyWrongCorrectFixedPasswordGenerator());

        //then
        assertThat(myuser.getPassword()).isNull();

    }

}
