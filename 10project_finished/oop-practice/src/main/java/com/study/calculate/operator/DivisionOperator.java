package com.study.calculate.operator;

public class DivisionOperator implements NewArithmeticOperator {
    @Override
    public boolean supperots(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
//        if(operand2.toInt()==0) {
//            throw new IllegalArgumentException("0으로 나눌수 없습니다");
//        }   positiveNumber를 썻기때문에 더 이상 필요 없음
        return operand1.toInt()/operand2.toInt();
    }
}
