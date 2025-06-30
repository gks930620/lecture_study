package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import sample.cafekiosk.unit.beverage.Americano;
import org.junit.jupiter.api.Test;

public class AmericanoTest {


    @Test
    void getName(){
        Americano americano=new Americano();
        //assertEquals(americano.getName(),"아메리카노"); //junit 기본 방식

        //assertj 라이브러리 사용하는 방식
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }


    @Test
    void getPrice(){
        Americano americano=new Americano();
        assertThat(americano.getPrice()).isEqualTo(4000);
    }



}
