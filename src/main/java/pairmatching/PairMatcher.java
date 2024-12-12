package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.crew.CrewInformation;

public class PairMatcher {
    public List<List<String>> runMatching(CrewInformation crewInformation, List<String> courseLevelMission) {
        List<String> shuffledCrewNames = getShuffledCrewNames(crewInformation, courseLevelMission.getFirst());
        int crewNumber = shuffledCrewNames.size();
        List<List<String>> pair = new ArrayList<>();

        if (crewNumber % 2 == 0) {
            evenNumberLogic(crewNumber, pair, shuffledCrewNames);
        }
        if (crewNumber % 2 != 0) {
            oddNumberLogic(crewNumber, pair, shuffledCrewNames);
        }

        return pair;
    }

    private void evenNumberLogic(int crewNumber, List<List<String>> pair, List<String> shuffledCrewNames) {
        for (int number = 0; number < crewNumber; number += 2) {
            pair.add(List.of(shuffledCrewNames.get(number), shuffledCrewNames.get(number + 1)));
        }
    }

    private void oddNumberLogic(int crewNumber, List<List<String>> pair, List<String> shuffledCrewNames) {
        int number;
        for (number = 0; number < crewNumber - 3; number += 2) {
            pair.add(List.of(shuffledCrewNames.get(number), shuffledCrewNames.get(number + 1)));
        }

        pair.add(List.of(shuffledCrewNames.get(number), shuffledCrewNames.get(number + 1),
                shuffledCrewNames.get(number + 2)));
    }

    private List<String> getShuffledCrewNames(CrewInformation crewInformation, String course) {
        List<String> allCrewNames = new ArrayList<>();
        if (course.equals("백엔드")) {
            allCrewNames = crewInformation.getBackEndCrewNames();
        }
        if (course.equals("프론트엔드")) {
            allCrewNames = crewInformation.getFrontEndCrewNames();
        }

        return Randoms.shuffle(allCrewNames);
    }
}
