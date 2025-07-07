package sample.cafekiosk.spring.domain.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    @Test
    public  void  isQuantityLessThan() throws Exception {
        //given
        int quantity=2;
        Stock stock1=Stock.create("001" , 1);
        //when
        boolean result = stock1.isQuantityLessThan(quantity);
        //then
        assertThat(result).isEqualTo(true);
    }


    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    @Test
    public  void  deductQuantity() throws Exception {
        //given
        Stock stock1=Stock.create("001" , 1);
        int quantity=1;
        //when
        stock1.deductQuantity(quantity);
        //then
        assertThat(stock1.getQuantity()).isEqualTo(0);
    }

    @DisplayName("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.")
    @Test
    public  void  deductQuantity2() throws Exception {
        //given
        Stock stock1=Stock.create("001" , 1);
        int quantity=2;



        //  when  then
        assertThatThrownBy(() -> stock1.deductQuantity(quantity))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("차감할 재고 수량이 없습니다.");
    }

}