package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.SelectedTypeStudyCafePasses;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }


    //여기도 ENUM or csv 에 따라..
    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showPassListForSelection(SelectedTypeStudyCafePasses studyCafePasses) {
        System.out.println();
        System.out.println("이용권 목록");
        List<StudyCafePass> studyCafePassList= studyCafePasses.getCafePasses();
        for (int index = 0; index < studyCafePassList.size(); index++) {
            StudyCafePass pass = studyCafePassList.get(index);
            System.out.println(String.format("%s. ", index + 1) + displayCafeePass(pass));
        }

    }

    public void askLockerPass(StudyCafePass studyCafePass) {
        System.out.println();
        if (studyCafePass.getLockerPass() != null) {
            String askMessage = String.format(
                "사물함을 이용하시겠습니까? (%s)",
                displayLockerPassInCafePass(studyCafePass)
            );
            System.out.println(askMessage);
            System.out.println("1. 예 | 2. 아니오");
        }
    }


    public void showPassOrderSummary(StudyCafePass selectedPass) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " +displayCafeePass(selectedPass));
        if (selectedPass.getLockerPass() != null) {
            System.out.println("사물함: " + displayLockerPassInCafePass(selectedPass));
        }

        int discountPrice = selectedPass.getDiscountPrice();
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice =   selectedPass.getTotalPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }


    private String displayLockerPassInCafePass(StudyCafePass cafePass) {
        StudyCafeLockerPass lockerPass=cafePass.getLockerPass();
        return String.format("%s주권 - %d원", cafePass.getDuration(), lockerPass.getPrice());
    }

    public String displayCafeePass(StudyCafePass studyCafePass) {
        return String.format(studyCafePass.getPassType().getOutputMessage(), studyCafePass.getDuration(), studyCafePass.getPrice());
    }


}
