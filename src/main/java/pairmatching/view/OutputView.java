package pairmatching.view;

import java.util.List;

public class OutputView {
    public void printOutputMessage(String message) {
        System.out.println(message);
    }

    public void printPairMatchingResult(List<List<String>> matchedPair) {
        System.out.println("페어 매칭 결과입니다.");
        for(List<String> pair:matchedPair){
            if(pair.size()==2) {
                System.out.println(pair.getFirst() + " : " + pair.get(1));
            }
            if(pair.size()==3){
                System.out.println(pair.getFirst() + " : " + pair.get(1)+ " : "+pair.get(2));
            }
        }
    }

    public void printResetResult() {
        System.out.println("초기화 되었습니다.");
    }
}
