package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.crew.CrewInformation;

public class PairMatcher {
    public List<List<String>> runMatching(CrewInformation crewInformation, List<String> pairMatchingInfo){
        List<String> shuffledCrewNames  =getShuffledCrewNames(crewInformation,pairMatchingInfo.getFirst());
        int crewNumber = shuffledCrewNames.size();

        List<List<String>> pair = new ArrayList<>();

        if (crewNumber % 2 != 0) {
            int number;
            for (number = 0; number < crewNumber - 3; number += 2) {
                pair.add(List.of(shuffledCrewNames.get(number), shuffledCrewNames.get(number + 1)));
            }

            pair.add(List.of(shuffledCrewNames.get(number), shuffledCrewNames.get(number + 1),
                    shuffledCrewNames.get(number + 2)));
        }

        if (crewNumber % 2 == 0) {

            for (int number = 0; number < crewNumber; number += 2) {
                pair.add(List.of(shuffledCrewNames.get(number), shuffledCrewNames.get(number + 1)));
            }
        }

        return pair;
    }

    private List<String> getShuffledCrewNames(CrewInformation crewInformation, String course){
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
