package pairmatching;

public enum ErrorMessage {
    INVALID_COURSE("유효하지 않은 과정입니다."),
    INVALID_LEVEL("유효하지 않은 레벨입니다."),
    INVALID_MISSION("유효하지 않은 미션입니다."),

    NULL_OR_EMPTY_INPUT("입력값은 비어있을 수 없습니다."),
    BOOLEAN_INPUT_MISMATCH("입력값은 '%s' 또는 '%s'이어야 합니다."),

    INVALID_FUNCTION("입력값이 유효하지 않습니다."),

    NULL_OR_EMPTY_LIST("리스트는 비어있을 수 없습니다."),
    INVALID_LIST_SIZE("리스트에는 %d개의 항목이 있어야 합니다.");


    private final String message;
    private final String ERROR_PREFIX="[ERROR] ";

    ErrorMessage(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(ERROR_PREFIX + message, args);
    }
}