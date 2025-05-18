package com.study.calculate;

import com.study.calculate.operator.*;

import java.util.List;

public class Calculator {
    private static  final List<NewArithmeticOperator> arithmeticOperators=
            List.of(new AdditionOperator(),new SubtractionOperator(),new MultiplicationOperator(),new DivisionOperator());


    //enum방식
//    public static int calculate(int operand1, String operator, int operand2) {
//        return ArithmeticOperator.calculate(operand1,operator,operand2);
//    }


    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        Integer result = arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supperots(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
        return  result;
    }

}
