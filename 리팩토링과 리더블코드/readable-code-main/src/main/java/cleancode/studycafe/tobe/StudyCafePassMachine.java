package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafePassCsvReader;
import cleancode.studycafe.tobe.io.StudyCafePassReader;
import cleancode.studycafe.tobe.model.SelectedTypeStudyCafePasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

   private final StudyCafePassReader studyCafePassReader;

    public StudyCafePassMachine(StudyCafePassReader studyCafePassReader) {
        this.studyCafePassReader = studyCafePassReader;
    }

    public void run() {

        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();
            outputHandler.askPassTypeSelection();


            StudyCafePass selectedPass=selectPass();

            if ( StudyCafePassType.canUseLockerType(selectedPass.getPassType()) ) {
                outputHandler.askLockerPass(selectedPass);
                if (inputHandler.getLockerSelection()) {
                    selectedPass.makeLockerPassUsed();
                }
            }
            outputHandler.showPassOrderSummary(selectedPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }


    private StudyCafePass selectPass(){
     SelectedTypeStudyCafePasses studyCafePasses ;
        StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
        List<StudyCafePass>  allStucafePasses= studyCafePassReader.readStudyCafePasses();
        studyCafePasses=new SelectedTypeStudyCafePasses(allStucafePasses,studyCafePassType);

        outputHandler.showPassListForSelection(studyCafePasses);
        int selectedIndex = inputHandler.getSelectPass();
        StudyCafePass selectedPass = studyCafePasses.chooseStudyCafePass(selectedIndex);
        return selectedPass;
    }
}
