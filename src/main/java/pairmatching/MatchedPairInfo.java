package pairmatching;

import java.util.List;

public class MatchedPairInfo {
    private final List<String> information;
    private final List<List<String>> matchedCrewInfo;

    public MatchedPairInfo(List<String> information, List<List<String>> matchedCrewInfo){
        this.information=information;
        this.matchedCrewInfo = matchedCrewInfo;
    }

    public List<String> getInformation(){
        return information;
    }

    public void addMatchedCrew(List<String> pair){
        matchedCrewInfo.add(pair);
    }

    public List<List<String>> getMatchedCrewInfo(){
        return matchedCrewInfo;
    }

    public boolean isValidMatching(List<List<String>> pairResult){
        for(List<String> pair:pairResult){
            if(matchedCrewInfo.contains(pair)){
                return false;
            }
        }
        return true;
    }
}
