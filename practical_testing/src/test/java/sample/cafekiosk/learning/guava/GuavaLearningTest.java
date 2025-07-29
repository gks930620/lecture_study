package sample.cafekiosk.learning.guava;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class GuavaLearningTest {


    @DisplayName("딱 떨어지는 숫자로 List를 파티셔닝한다")
    @Test
    public  void  listTest () throws Exception {
        //given
        List<Integer> ingegers= List.of(1,2,3,4,5,6);

        //when
        List<List<Integer>> partition = Lists.partition(ingegers, 3);

        //then
        assertThat(partition).hasSize(2)
            .isEqualTo(  List.of(
                List.of(1,2,3), List.of(4,5,6)
            ));
    }

    @DisplayName("떨어지지않는 숫자로 List를 파티셔닝한다")
    @Test
    public  void  listPartionTestUndivded () throws Exception {
        //given
        List<Integer> ingegers= List.of(1,2,3,4,5,6,7);

        //when
        List<List<Integer>> partition = Lists.partition(ingegers, 4);

        //then
        assertThat(partition).hasSize(2)
            .isEqualTo(  List.of(
                List.of(1,2,3,4), List.of(5,6,7)
            ));
    }


    @DisplayName("멀티탭 동일이름 put 확인 ")
    @Test
    public  void  multimap () throws Exception {
        //given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피" , "아메리카노");
        multimap.put("커피" , "카페라떼");
        multimap.put("커피" , "카푸치노");
        multimap.put("빵" , "식빵");
        multimap.put("빵" , "보름달");

        //when
        Collection<String> 커피스 = multimap.get("커피");

         //then
        assertThat(커피스).hasSize(3).isEqualTo(List.of("아메리카노","카페라떼","카푸치노"));
    }

    //순서가 중요할 때 순서대로 
    @DisplayName("멀티탭 동일이름 put 확인 ")
    @TestFactory
    Collection<DynamicTest>  multimapLearning여러개 () throws Exception {
        //given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피" , "아메리카노");
        multimap.put("커피" , "카페라떼");
        multimap.put("커피" , "카푸치노");
        multimap.put("빵" , "식빵");
        multimap.put("빵" , "보름달");


        return List.of(
            DynamicTest.dynamicTest("1개 value 삭제" , ()->{
                multimap.remove("커피","카푸치노"); //when
                Collection<String> result= multimap.get("커피");
                assertThat(result).hasSize(2)
                    .isEqualTo(List.of("아메리카노","카페라떼"));
            }),
            DynamicTest.dynamicTest("1개 value 삭제" , ()->{  //순서에 영향을 미침.
                Collection<String> result= multimap.get("커피");
                assertThat(result).hasSize(2)
                    .isEqualTo(List.of("아메리카노","카페라떼"));
            }),
            DynamicTest.dynamicTest("1개 key 삭제" , ()->{
                multimap.removeAll("커피"); //when
                Collection<String> result= multimap.get("커피");
                assertThat(result).isEmpty();
            })
        );
    }


}
