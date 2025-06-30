package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();
        return StudyCafePassType.findStudyCafeePassTypeWithNumber(Integer.parseInt(userInput));
    }

    public int getSelectPass() {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) ;
        return selectedIndex;
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
