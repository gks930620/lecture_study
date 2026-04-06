package cleancode.studycafe.tobe.model;

public class StudyCafeLockerPass {  //VO

    private boolean isUsed=false;
    private final int price;

    private StudyCafeLockerPass(int price) {
        this.price=10000;
    }


    public static StudyCafeLockerPass of( int price) {
        return new StudyCafeLockerPass(price);
    }


    public int getPrice() {
        return price;
    }

    public void makeUsed(){
        this.isUsed=true;
    }

}
