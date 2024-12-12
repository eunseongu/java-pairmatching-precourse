package pairmatching;

import java.util.List;
import pairmatching.crew.Crew;
import pairmatching.crew.CrewInformation;
import pairmatching.view.OutputView;

public class Controller {

    private final InputHandler inputHandler;
    private final OutputView outputView;

    public Controller(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {

        CrewInformationLoader crewInformationLoader = new CrewInformationLoader();
        CrewInformation crewInformation= crewInformationLoader.getCrewInformation();
        List<Crew> allCrewInformation=crewInformation.getAllCrewInformation();

        String functionInput = inputHandler.handleFunctionInput();
        if(functionInput.equals("1")){
            pairMatching();
        }
        if(functionInput.equals("2")){
            checkPair();
        }
        if(functionInput.equals("3")){
            resetPair();
        }
        if(functionInput.equalsIgnoreCase("Q")){
            quit();
        }
    }

    public void pairMatching(){
        List<String> pairMatchingInfo = inputHandler.handlePariMatchingInfoInput();

        outputView.printOutputMessage("pairMatchingInfo: " + pairMatchingInfo); /////////
    }

    public void checkPair(){

    }

    public void resetPair(){

    }

    public void quit(){

    }
}

