package pairmatching;

import java.util.List;
import pairmatching.constants.ErrorMessage;
import pairmatching.constants.pairMatchingInformation;

public class InputValidator {
    public void validateNotNullOrEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY_INPUT.format());
        }
    }

    public void validateFunctionInput(String input) {
        if (!List.of("1","2","3","Q","q").contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FUNCTION.format());
        }
    }

    public void validateBooleanInput(String input) {
        if (!input.equals("네") && !input.equals("아니오")) {
            throw new IllegalArgumentException(ErrorMessage.BOOLEAN_INPUT_MISMATCH.format("네", "아니오"));
        }
    }


    public void validatePariMatchingInfo(List<String> list) {
        if (!pairMatchingInformation.COURSE.contains(list.getFirst())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_COURSE.format());
        }
        if (list.get(1).equals("레벨3")||list.get(1).equals("레벨5")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LEVEL.format());
        }
        if (!pairMatchingInformation.LEVEL.contains(list.get(1))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LEVEL.format());
        }

        validateMission(list.get(1),list.get(2));
    }

    private void validateMission(String level, String mission){
        if(level.equals("레벨1") && !pairMatchingInformation.LEVEL_ONE.contains(mission)){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MISSION.format());
        }
        if(level.equals("레벨2") && !pairMatchingInformation.LEVEL_TWO.contains(mission)){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MISSION.format());
        }
        if(level.equals("레벨4") && !pairMatchingInformation.LEVEL_FOUR.contains(mission)){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MISSION.format());
        }
    }

    public void validateNotNullOrEmptyList(List<String> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY_LIST.format());
        }
    }

    public void validateListSize(List<String> list, int size) {
        if (list.size() != size) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LIST_SIZE.format(size));
        }
    }
}
