package numberbaseball;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import numberbaseball.NumberBaseBall.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberBaseBallTest {
    //makeRandoms stringToIntArr   numbersCheck (이 안에 numberOne체크가 이ㅏㅆ으니까 )  이렇게 테스트해보면 될듯
    //static이여도 기본값으로  ball, strike가 0이기때문에 test해도 됨. 끝나면 0으로 바꾸기. AfterAll 로?

    @Test
    void makeRandomsTest() {
        List<Integer> list = NumberBaseBall.makeRandoms();
        assertThat(list).hasSize(3);
        Set<Integer> unique = new HashSet<>(list);
        assertThat(unique).hasSize(3);    //set으로 바꿔도 size3. 중복되지않은걸 확인
    }

    @Test
    void stringToIntArrTest() {
        String input="123";
        List<Integer> list = NumberBaseBall.stringToIntArr(input);
        assertThat(list).containsExactly(1,2,3);
    }


    //결과는 맞는거같은데... 테스트 성공하게 다시 해보자   gralde문제느 아닌거같고..   main에서 확인해보던가
    @ParameterizedTest
    @MethodSource("listProvider")
    void numbersCheckTest(List<Integer> guess, Result expected) {   //파라미터라이즈드 하기로하지않았나..
        List<Integer> answers=List.of(3,5,9);
        Result result = NumberBaseBall.numbersCheck(guess, answers);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> listProvider() {
        return Stream.of(
            Arguments.of(List.of(1,2,3), Result.B1),
            Arguments.of(List.of(5,3,9), Result.B2S1),
            Arguments.of(List.of(9,3,5), Result.B3),
            Arguments.of(List.of(3,5,9), Result.S3)
        );
    }


}