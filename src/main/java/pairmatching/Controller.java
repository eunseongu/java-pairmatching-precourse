package pairmatching;

import java.util.ArrayList;
import java.util.List;
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
        CrewInformation crewInformation = crewInformationLoader.getCrewInformation();
        List<MatchedPairInfo> matchedPairInfo = new ArrayList<>();

        while (true) {
            String functionInput = inputHandler.handleFunctionInput();
            if (functionInput.equals("1")) {
                pairMatching(crewInformation, matchedPairInfo);
            }
            if (functionInput.equals("2")) {
                checkPair(matchedPairInfo);
            }
            if (functionInput.equals("3")) {
                resetPair(matchedPairInfo);
            }
            if (functionInput.equalsIgnoreCase("Q")) {
                break;
            }
        }
    }

    public void pairMatching(CrewInformation crewInformation, List<MatchedPairInfo> matchedPairInfo) {
        List<String> courseLevelMission = inputHandler.handlePariMatchingInfoInput();
        String course=courseLevelMission.getFirst();
        String level=courseLevelMission.get(1);

        List<List<String>> pairResult = new ArrayList<>();
        PairMatcher pairMatcher = new PairMatcher();

        int sameMatchedPairIdx = findSameMatchedPair(courseLevelMission, matchedPairInfo);
        try{
        if (sameMatchedPairIdx != -1) {
            String rematchingInput = inputHandler.handleRematchingInput();
            if (rematchingInput.equals("네")) {
                matchedPairInfo.remove(sameMatchedPairIdx);

                pairResult = getPairResult(pairResult, pairMatcher, crewInformation, course, level);
            }
        } else {
            pairResult = getPairResult(pairResult, pairMatcher, crewInformation, course, level);
        }

        matchedPairInfo.add(new MatchedPairInfo(courseLevelMission, pairResult));
        crewInformation.updateMatchedCrew(pairResult,level);

        outputView.printPairMatchingResult(pairResult);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<List<String>> getPairResult(List<List<String>> pairResult, PairMatcher pairMatcher,
                                             CrewInformation crewInformation, String course, String level) {

        pairResult = pairMatcher.runMatching(crewInformation, course);

        int count = 0;
        while (!isValidMatchingCrewVer(pairResult, level, crewInformation)) {
            count++;
            pairResult = pairMatcher.runMatching(crewInformation, course);
            if (count == 3) {
                throw new IllegalArgumentException("[ERROR] 매칭에 실패했습니다.");
            }
        }
        return pairResult;
    }

    private boolean isValidMatchingCrewVer(List<List<String>> pairResult, String level,CrewInformation crewInformation) {

        for(List<String> pair:pairResult){
            if(pair.size()==2){
                if(crewInformation.findCrew(pair.getFirst()).isAlreadyMatched(pair.get(1),level)){
                    return false;
                }
            }
            if(pair.size()==3){
                if(crewInformation.findCrew(pair.getFirst()).isAlreadyMatched(pair.get(1),level) ||
                        crewInformation.findCrew(pair.getFirst()).isAlreadyMatched(pair.get(2),level)){
                    return false;
                }
            }
        }
        return true;
    }


    public int findSameMatchedPair(List<String> pairMatchingInfo, List<MatchedPairInfo> matchedPairInfo) {
        for (int i = 0; i < matchedPairInfo.size(); i++) {
            if (matchedPairInfo.get(i).getInformation().equals(pairMatchingInfo)) {
                return i;
            }
        }
        return -1;
    }

    public void checkPair(List<MatchedPairInfo> matchedPairInfo) {
        List<String> pairMatchingInfo = inputHandler.handlePariMatchingInfoInput();

        try {
            boolean checker = false;
            for (MatchedPairInfo pair : matchedPairInfo) {
                if (pair.getInformation().equals(pairMatchingInfo)) {
                    outputView.printPairMatchingResult(pair.getMatchedPair());
                    checker = true;
                }
            }

            if (!checker) {
                throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public void resetPair(List<MatchedPairInfo> matchedPairInfo) {
        matchedPairInfo.clear();
        outputView.printResetResult();
    }
}

