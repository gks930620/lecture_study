package com.study.calculate;

import com.study.calculate.operator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
public class PositiveNumberTest {

    @DisplayName("양수가 아닐 때는 에러")
    @ParameterizedTest
    @ValueSource(ints ={0,-1})
    void createTest(int value) {
        assertThatCode(()-> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0또는 음수를  전달할 수 없습니다.");

    }
}
