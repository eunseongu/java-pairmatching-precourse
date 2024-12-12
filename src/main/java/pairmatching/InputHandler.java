package pairmatching;

import java.util.List;
import pairmatching.view.InputView;

public class InputHandler {

    private final InputView inputView;
    private final InputValidator inputValidator;

    public InputHandler(InputView inputView, InputValidator inputValidator) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
    }

    public String handleFunctionInput() {
        while (true) {
            String input = inputView.readFunctionMessage();
            try {
                inputValidator.validateNotNullOrEmpty(input);
                inputValidator.validateFunctionInput(input);

                return input;
            } catch (IllegalArgumentException e) {
                inputView.printErrorMessage(e.getMessage());
            }
        }
    }

    // 리스트 입력 처리 프론트엔드, 레벨1, 자동차경주
    public List<String> handlePariMatchingInfoInput() {
        String delimiter = ",";
        while (true) {
            inputView.printInfoMessage();
            String input = inputView.readPariMatchingInfo();
            try {
                List<String> parsedList = Parser.parseList(input, delimiter);


                inputValidator.validateNotNullOrEmptyList(parsedList);
                inputValidator.validateListSize(parsedList,3);
                inputValidator.validatePariMatchingInfo(parsedList);

                return parsedList;
            } catch (IllegalArgumentException e) {
                inputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public List<String> TMP() {
        String delimiter = ",";
        while (true) {
            String input = "프론트엔드, 레벨1, 자동차경주";
            try {
                List<String> parsedList = Parser.parseList(input, delimiter);

                inputValidator.validateNotNullOrEmptyList(parsedList);

                return parsedList;
            } catch (IllegalArgumentException e) {
                inputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
