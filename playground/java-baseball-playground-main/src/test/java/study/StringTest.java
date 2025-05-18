package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }
    //요구사항 1.
    // "1,2"를 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트
    // "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
    @Test
    void split(){
        String actual="1,2";
        String[] splits = actual.split(",");
        assertThat(splits).contains("1","2");
        assertThat(splits).containsExactly("1","2");   //정확히 "1","2"일 때만 true
    }
    @Test
    void split2(){
        String actual="1";
        String[] splits = actual.split(",");
        assertThat(splits).containsExactly("1");
        assertThat(splits).hasSize(1);
    }
    //요구사항2. "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
    @Test
    void substring(){
        String actual="(1,2)";
        String substring = actual.substring(1, 4);
        assertThat(substring).isEqualTo("1,2");
    }

    //요구사항 3
    //"abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
    //String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
    //JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
    @Test
    @DisplayName("String의 charAt이 범위가 넘어갈 때 StringIndexOutOfBoundsException가 발생한다. "
        + "메세지는 String index out of range를 포함한다.")
    void charAt(){
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
            .isThrownBy(()->{
                String actual="abc";
                char c = actual.charAt(actual.length());
            }).withMessageContaining("String index out of range");;
    }
}






