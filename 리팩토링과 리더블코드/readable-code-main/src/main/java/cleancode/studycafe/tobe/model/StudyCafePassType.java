package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.exception.AppException;
import java.util.Arrays;
import java.util.Set;

public enum StudyCafePassType {

    HOURLY(1, "시간 단위 이용권", "%s시간권 - %d원"),
    WEEKLY(2, "주 단위 이용권", "%s주권 - %d원"),
    FIXED(3, "1인 고정석", "%s주권 - %d원");

    private final int userInputSelectionNumber;
    private final String description;
    private final String outputMessage;

    public static final Set<StudyCafePassType> CAN_USE_LOCKER_TYPES
        =Set.of(FIXED);

    public static boolean canUseLockerType(StudyCafePassType type){
        return CAN_USE_LOCKER_TYPES.contains(type);
    }

    StudyCafePassType(int userInputSelectionNumber, String description, String outputMessage) {
        this.userInputSelectionNumber = userInputSelectionNumber;
        this.description = description;
        this.outputMessage = outputMessage;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public static StudyCafePassType findStudyCafeePassTypeWithNumber(int number) {
        return Arrays.stream(values())
            .filter(cafePassType -> cafePassType.userInputSelectionNumber == number)
            .findFirst().orElseThrow(() -> new AppException(" 이용타입은 1~3만 선택 가능"));
    }

}