package com.study.calculate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.study.calculate.Calculator;
import com.study.calculate.operator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 간단한 사칙연산
 * 양수로만 계산
 * 나눗셈에서 0을 나누면 IllegalArgument
 * MVC패턴 기반으로 구현
 */
public class CalculatorTest {

    //사칙연산테스트를 한번에.

    @DisplayName("덧셈연산을 수행한다")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1,String operator,int operand2,int expectedResult) {
        int calculateResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        assertThat(calculateResult).isEqualTo(expectedResult);
    }

   private static Stream<Arguments> formulaAndResult(){
        return  Stream.of(arguments(1,"+",2,3),
                arguments(1,"-",2,-1),
                arguments(4,"*",2,8),
                arguments(4,"/",2,2)
        );
    }



    //positiveNumber를 사용하면 이 메소드는 필요없고, positiveNumber에 대한 테스트가 필요
//    @DisplayName("나눗셈에서 0을 나누는경우 IllegalArgument 발생")
//    @Test
//    void calculateExceptionTest() {
//        assertThatCode(()-> Calculator.calculate(new PositiveNumber(10),"/",new PositiveNumber(10)))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("0으로 나눌수 없습니다");
//    }
}
