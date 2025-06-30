package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.StudyCafePassCsvReader;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine( new StudyCafePassCsvReader());
        studyCafePassMachine.run();
    }

}
