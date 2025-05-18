package study;

import static org.assertj.core.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


public class SetTest {
    private  Set<Integer> numbers;
    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    void size(){
        assertThat(numbers).hasSize(3);
    }

    /*
    Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현하려한다.
    구현하고 보니 다음과 같이 중복 코드가 계속해서 발생한다.
    JUnit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.
     */
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})  
    void contains(int number){
        assertThat(numbers).contains(number);   //numbers가 1,2,3이 있는지 확인
    }


    //예를 들어 1, 2, 3 값은 contains 메소드 실행결과 true
    // 4, 5 값을 넣으면 false 가 반환되는 테스트를 하나의 Test Case로 구현한다.
    @ParameterizedTest
    @CsvSource( value = {"1,true","2,true","3,true","4,false","5,false"})   //true/false는 자동으로 boolean타입으로 변환
    void containsCsv(int number, boolean expected){
        assertThat(numbers.contains(number)).isEqualTo(expected);   //numbers가 1,2,3이 있는지 확인
    }

}