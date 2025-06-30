package cleancode.studycafe.tobe.model;

public class StudyCafePass {  //VO

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeLockerPass lockerPass;

    private StudyCafePass(StudyCafePassType passType, int duration, int price,
        double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;

        //기간이 같은거에 대해서 ENUM이든 DB든 가져온다고 하면 if 안해도 될거같음.  DB에 있다면 같은 기간의 그것을 가지고 와서 resultmap
//        if(this.passType==StudyCafePassType.FIXED){
//            if(this.duration==4){
//                this.lockerPass=StudyCafeLockerPass.of(10000);
//            }else{
//                this.lockerPass=StudyCafeLockerPass.of(30000);
//            }
//        }
        if( StudyCafePassType.canUseLockerType(this.passType) ) {
            if (this.duration == 4) { //지금 당장은 사물함이 스터디카페 기간이랑 같아야하고 가격만 따로 포함인거지.
                this.lockerPass = StudyCafeLockerPass.of(10000);
            } else {
                this.lockerPass = StudyCafeLockerPass.of(30000);
            }
        }


    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price,
        double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }


    public StudyCafeLockerPass getLockerPass() {
        return lockerPass;
    }


    public void makeLockerPassUsed() {
        this.lockerPass.makeUsed();
    }


    public int getDiscountPrice() {
        double discountRate = this.getDiscountRate();
        int discountPrice = (int) (this.getPrice() * discountRate);
        return discountPrice;
    }

    public int getTotalPrice() {
        return this.getPrice() - getDiscountPrice() + (this.getLockerPass() != null ? this.getLockerPass().getPrice() : 0);
    }
}
