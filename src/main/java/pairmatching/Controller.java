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
        List<List<String>> pairResult = new ArrayList<>();
        PairMatcher pairMatcher = new PairMatcher();

        int sameMatchingInfoIdx = getSameMatchingInformation(courseLevelMission, matchedPairInfo);
        if (sameMatchingInfoIdx != -1) {
            String rematchingInput = inputHandler.handleRematchingInput();
            if (rematchingInput.equals("네")) {
                matchedPairInfo.remove(sameMatchingInfoIdx);

                pairResult = pairMatcher.runMatching(crewInformation, courseLevelMission);

                while (!isValidMatching(pairResult, matchedPairInfo, courseLevelMission)) {
                    pairResult = pairMatcher.runMatching(crewInformation, courseLevelMission);
                }
            }
        } else {
            pairResult = pairMatcher.runMatching(crewInformation, courseLevelMission);

            while (!isValidMatching(pairResult, matchedPairInfo, courseLevelMission)) {
                pairResult = pairMatcher.runMatching(crewInformation, courseLevelMission);
            }
        }
        matchedPairInfo.add(new MatchedPairInfo(courseLevelMission, pairResult));

        outputView.printPairMatchingResult(pairResult);
    }

    private boolean isValidMatching(List<List<String>> pairResult, List<MatchedPairInfo> matchedPairInfo,
                                    List<String> courseLevelMission) {
        String inputLevel = courseLevelMission.get(1);
        for (MatchedPairInfo info : matchedPairInfo) {
            String matchedInfoLevel = info.getInformation().get(1);
            if (matchedInfoLevel.equals(inputLevel)) {
                return info.isValidMatching(pairResult);
            }
        }
        return true;
    }


    public int getSameMatchingInformation(List<String> pairMatchingInfo, List<MatchedPairInfo> matchedPairInfo) {
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
                    outputView.printPairMatchingResult(pair.getMatchedCrewInfo());
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

