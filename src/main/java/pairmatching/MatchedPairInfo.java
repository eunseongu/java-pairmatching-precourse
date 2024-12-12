package pairmatching;

import java.util.List;

public class MatchedPairInfo {
    private final List<String> information;
    private final List<List<String>> matchedPair;

    public MatchedPairInfo(List<String> information, List<List<String>> matchedPair){
        this.information=information;
        this.matchedPair = matchedPair;
    }

    public List<String> getInformation(){
        return information;
    }

    public void addMatchedCrew(List<String> pair){
        matchedPair.add(pair);
    }

    public List<List<String>> getMatchedPair(){
        return matchedPair;
    }

    public boolean isValidMatching(List<List<String>> pairResult){
        for(List<String> pair:pairResult){
            if(matchedPair.contains(pair)){
                return false;
            }
        }
        return true;
    }
}
