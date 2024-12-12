package pairmatching;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputValidator inputValidator = new InputValidator();
        InputHandler inputHandler = new InputHandler(inputView, inputValidator);
        OutputView outputView = new OutputView();
        Controller controller = new Controller(inputHandler, outputView);

        controller.run();
    }
}

