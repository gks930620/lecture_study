package com.study.password;

import com.study.password.PasswordValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 비밀번호는 최소 8자 이상 12자 이하
 * 위 조건이 아니면 IlleagalArgumentException
 * 경계조건 테스트 꼭
 */
public class PasswordValidatorTest {


    @DisplayName("비밀번호는 최소 8자 이상 12자 이하면 예외가 발생하지 않는다")
    @Test
    void validatePasswordTest(){
        assertThatCode( () -> PasswordValidator.validate("serverwizard"))
                .doesNotThrowAnyException();
    }


    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IlleagalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"aabbcce" , "aabbccddeeffg"})  //7, 13글자
    void validatePasswordTest2(String password){
        assertThatCode( () -> PasswordValidator.validate(password) )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PasswordValidator.WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);


    }


}
