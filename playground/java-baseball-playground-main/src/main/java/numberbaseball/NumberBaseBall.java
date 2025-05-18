package numberbaseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class NumberBaseBall {

    //위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다.
    // 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다.
    // 게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
    //이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
    //게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.

    public static Scanner scanner=new Scanner(System.in);
    public static void main(String args[]){
        int gameEnd=1;
        while(gameEnd==1){
            gameStart();
            System.out.println("게임 진행 여부.  1:  다시시작,   2: 종료");
            gameEnd = scanner.nextInt();
        }
    }

    public static void gameStart() {
        List<Integer> answers= List.of(3,5,9);// makeRandoms();
        System.out.println("확인용 : 정답은 "+ answers);
        Result result=Result.NO;
        while (!result.equals(Result.S3)){
            System.out.print("숫자를 입력해주세요 : ");
            String input=scanner.nextLine();
            List<Integer> guess=stringToIntArr(input);
            result= numbersCheck(guess,answers);
            System.out.println(result.message);
        }
        System.out.println("정답은 " + answers +"입니다");
    }

    public  static List<Integer> makeRandoms() {
        Set<Integer> set=new HashSet<>();
        Random random=new Random();   //중복안되는로직해야지
        while (set.size()<3){
            set.add(random.nextInt(10));
        }
        return new ArrayList<>(set);
    }  //테스트


    public static List<Integer>  stringToIntArr(String input){  //테스트
        List<Integer> list= new ArrayList<>();
        for(int i=0 ; i<input.length() ; i++){
            list.add(Integer.parseInt(input.substring(i,i+1)));
        }
        return list;
    }

    public static Result numbersCheck( List<Integer> guess , List<Integer> answers) {  //파라미터라이즈드테스트
        List<Integer> bs=new ArrayList<>(List.of(0,0));   //b,s
        for(int i=0; i< guess.size() ; i++){
            numberOneCheck(guess.get(i),i,answers,bs);
        }

        return Result.getFromStrikeAndBall(bs.get(0),bs.get(1));
    }

    public static void numberOneCheck(int num, int index,List<Integer> answers,List<Integer> bs){
        if( !answers.contains(num)){
            return ;
        }
        if( answers.get(index)==num  ){
            bs.set(1,bs.get(1)+1); //strike
          return ;
        }
        bs.set(0,bs.get(0)+1); //ball
    }


    //결과: YES  , S2B1, S1B2, B3, NO
    enum Result{
        NO("out"),
        S1("1스트라이크"),
        S2("2스트라이크"),
        S3("3스트라이크"),
        B1("1볼"),
        B1S1("1볼1스트라이크"),
        B1S2("1볼2스트라이크"),
        B2("2볼"),
        B2S1("2볼1스트라이크"),
        B3("3볼");

        private final String message;
        Result(String message) {
            this.message=message;
        }

        public static Result getFromStrikeAndBall(int ball, int strike) {
            if(ball==0 && strike==0 ) return NO;
            if(ball==0 && strike==1) return S1;
            if(ball==0 && strike==2) return S2;
            if(ball==0 && strike==3) return S3;
            if(ball==1 && strike==0) return B1;
            if(ball==1 && strike==1) return B1S1;
            if(ball==1 && strike==2) return B1S2;
            if(ball==2 && strike==0) return B2;
            if(ball==2 && strike==1) return B2S1;
            return B3;
        }
    }


}
