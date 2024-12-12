package pairmatching;

import java.util.ArrayList;
import java.util.List;
import pairmatching.crew.Crew;
import pairmatching.crew.CrewInformation;
import pairmatching.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;

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
        List<MatchedPair>  matchedPair = new ArrayList<>();

        while(true) {
            String functionInput = inputHandler.handleFunctionInput();
//        String functionInput = inputHandler.FUNTMP();
            if (functionInput.equals("1")) {
                pairMatching(crewInformation, matchedPair);
            }
            if (functionInput.equals("2")) {
                checkPair();
            }
            if (functionInput.equals("3")) {
                resetPair();
            }
            if (functionInput.equalsIgnoreCase("Q")) {
                break;
            }
        }
    }

    public  void pairMatching(CrewInformation crewInformation,List<MatchedPair>  matchedPair){
        List<String> pairMatchingInfo = inputHandler.handlePariMatchingInfoInput();
//        List<String> pairMatchingInfo = inputHandler.TMP();


        List<String> allCrewNames = new ArrayList<>();
        if(pairMatchingInfo.getFirst().equals("백엔드")){
            allCrewNames  = crewInformation.getBackEndCrewNames();
        }
        if(pairMatchingInfo.getFirst().equals("프론트엔드")){
            allCrewNames  = crewInformation.getFrontEndCrewNames();
        }

        List<String> shuffledCrewNames = Randoms.shuffle(allCrewNames);


        int crewNumber= shuffledCrewNames.size();
        List<List<String>> pair= new ArrayList<>();

        // 프론트 홀수
        if(crewNumber%2!=0){
            int number;
            for(number=0;number<crewNumber-3;number+=2){
                pair.add(List.of(shuffledCrewNames.get(number),shuffledCrewNames.get(number+1)));
            }

            pair.add(List.of(shuffledCrewNames.get(number),shuffledCrewNames.get(number+1),shuffledCrewNames.get(number+2)));
        }
        // 백엔드 짝수
        if(crewNumber%2==0){

            for(int number=0;number<crewNumber;number+=2){
                pair.add(List.of(shuffledCrewNames.get(number),shuffledCrewNames.get(number+1)));
            }
        }

        matchedPair.add(new MatchedPair(pairMatchingInfo,pair));

        outputView.printPairMatchingResult(pair);
    }

    public void checkPair(){

    }

    public void resetPair(){

    }

    public void quit(){

    }
}

