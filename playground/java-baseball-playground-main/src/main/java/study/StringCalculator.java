package study;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Scanner;

public class StringCalculator {

    public static int calculate(String string){
        //사용자는 space(" ")로 숫자,사칙문자를 잘 구분한다고 가정.
        // 나누기 0을 입력하는 경우는 없다고 가정한다..
        String[] splits = string.split(" ");
        int result=Integer.parseInt(splits[0]);
        for(int i=1 ; i<splits.length ; i=i+2){   // operation은 홀수번째
            result=calculateOne(result , splits[i], Integer.parseInt(splits[i+1]) );
        }
        return result;
    }
    private static int calculateOne(int beforeNum, String operation, int afterNum  ){
        Operation op = Operation.fromSymbol(operation);
        return op.apply(beforeNum,afterNum);
    }

    enum Operation{
        PLUS("+"){
        @Override
        public int apply(int a, int b) {
            return a + b;
        } },
        MINUS("-") {
            @Override
            public int apply(int a, int b) {
                return a - b;
            }
        },
        MULTIPLY("*") {
            @Override
            public int apply(int a, int b) {
                return a * b;
            }
        },
        DIVIDE("/") {
            @Override
            public int apply(int a, int b) {
                if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                return a / b;
            }
        };


        private final String symbol;
        Operation(String symbol) {
            this.symbol = symbol;
        }
        public String getSymbol() {
            return symbol;
        }
        public abstract int apply(int a, int b);
        public static Operation fromSymbol(String symbol) {
            return Arrays.stream(Operation.values())
                .filter(op -> op.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 기호에 해당하는 연산자가 없습니다: " + symbol));
        }

    }

}
//요구사항
//사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
//문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다.
// 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
//예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
//    힌트
//문자열을 입력 받은 후(scanner의 nextLine() 메소드 활용) 빈 공백 문자열을 기준으로 문자들을 분리해야 한다.
//    String value = scanner.nextLine();
//String[] values = value.split(" ");
//문자열을 숫자로 변경하는 방법
//int number = Integer.parseInt("문자열");
