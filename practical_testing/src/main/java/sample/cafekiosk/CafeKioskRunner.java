package sample.cafekiosk;

import sample.cafekiosk.unit.CafeKiosk;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;
import java.time.LocalDateTime;

public class CafeKioskRunner {

    public static void main(String[] args) {
        CafeKiosk cafeKiosk=new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>>아메리카노 추가");

        cafeKiosk.add(new Latte());
        System.out.println(">>> 라떼 추가");

        int totalPrice=cafeKiosk.calculateTotalPrice();
        System.out.println("총 금액  : "  + totalPrice);

        Order order=cafeKiosk.createOrder(LocalDateTime.now());  //사용하는곳에서는  현재시간을 주기.  테스트하는곳에서는 원하는 시간 주기.. 
        // 테스트를 만들다보니  시간을 파라미터로 받는게 떠올랐음.  그전에는 내부에서 사용했었는데 이러면 테스트시점에 따라 테스트 성공여부가 달라졌음


        //시간 랜덤값, 전역변수/함수, 사용자 입력등은 테스트 할 때마다 값이 달라질수있는 테스트하기 어려운영역
        // 반대로 메세지 발송, 출력, DB insert/update/delete 등은 외부세계에 영향을 주는 코드도 테스트 어려움




    }

}
