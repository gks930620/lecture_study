package com.study.customer;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 1.도메인을 구성하는 객체에는 어떤 것들이 있는지 고민
 *  ㄴ 손님, 메뉴판(0), 요리사(0) , 요리(0) 돈가스/냉면, 만두(o)
 * 2. 객체들 관의 관계 고민
 *  ㄴ 손님 ---- 메뉴판
 *  ㄴ 손님 ---- 요리사
 *  ㄴ 요리사(o) --- 요리(o)
 * 
 * 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링하기( 객체들을 공통요소 => 클래스)
 *  ㄴ 손님 -- 손님타입
 *  ㄴ 돈가스/냉면/만두 -- 요리타입  ㄴ 메뉴 -- 메뉴타입  똑같나?
 *  ㄴ 메뉴판 -- 메뉴판 타입
 *
 *
 * 4. 협력 설계
 * 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
 * 6. 구현하기
 */
public class CustomerTest {

    @DisplayName("메뉴이름에 해당하는 요리를 주문한다.")
    @Test
    void orderTest() {
        Customer customer=new Customer();
        Menu menu= new Menu(List.of(new MenuItem("돈가스",5000) , new com.study.customer.MenuItem("냉면",7000)));
        Cooking cooking = new Cooking();
        assertThatCode(()-> customer.order("돈가스", menu,cooking) ).doesNotThrowAnyException();



    }
}
