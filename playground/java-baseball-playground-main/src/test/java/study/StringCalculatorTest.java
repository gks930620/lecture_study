package study;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    void calculateTest(){
        //요구사항의 문자열입력하라는게 의미가 있나.. 어차피 여기서의 util은 입력이 주어졌을 때 출력이나오는건데.
        String input= "2 + 3 * 4 / 2";
        int result= StringCalculator.calculate(input);
        assertThat(result).isEqualTo(10);
    }

}

