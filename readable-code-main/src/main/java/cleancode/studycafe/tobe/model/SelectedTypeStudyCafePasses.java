package cleancode.studycafe.tobe.model;

import java.util.List;

public class SelectedTypeStudyCafePasses {
    private final List<StudyCafePass> cafePasses;


    public SelectedTypeStudyCafePasses(     List<StudyCafePass>  list, StudyCafePassType studyCafePassType) {
        this.cafePasses=list.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
            .toList();
    }

    public StudyCafePass chooseStudyCafePass(int selectedIndex) {
        StudyCafePass selectedPass = cafePasses.get(selectedIndex - 1);
        return selectedPass;
    }

    public List<StudyCafePass> getCafePasses() {
        return cafePasses;
    }
}
