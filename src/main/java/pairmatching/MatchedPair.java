package pairmatching;

import java.util.ArrayList;
import java.util.List;
import pairmatching.crew.Crew;

public class MatchedPair {
    private final List<String> information;
    private final List<List<String>> matchedCrew ;

    public MatchedPair(List<String> information,List<List<String>> matchedCrew){
        this.information=information;
        this.matchedCrew=matchedCrew;
    }

    public List<String> getInformation(){
        return information;
    }

    public void addMatchedCrew(List<String> pair){
        matchedCrew.add(pair);
    }

}
