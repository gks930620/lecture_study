package com.study.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class CookingTest {

    @DisplayName("메뉴에 해당하는 음식을 만든다.")
    @Test
    void makeCookTest() {
        Cooking cooking=new Cooking();
        MenuItem menuItem=new MenuItem("돈가스",5000); //이건 테스트 완료됨
        Cook cook = cooking.makeCook(menuItem);
        assertThat(cook).isEqualTo(new Cook("돈가스" , 5000));

    }
}
