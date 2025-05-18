package com.study.calculate.operator;

import com.study.calculate.operator.NewArithmeticOperator;

public class SubtractionOperator implements NewArithmeticOperator {

    @Override
    public boolean supperots(String operator) {
        return "-".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt()-operand2.toInt();
    }
}
