package com.study.calculate.operator;

public class MultiplicationOperator implements NewArithmeticOperator {
    @Override
    public boolean supperots(String operator) {
        return "*".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt()*operand2.toInt();
    }
}
