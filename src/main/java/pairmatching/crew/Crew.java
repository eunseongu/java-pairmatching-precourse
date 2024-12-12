package pairmatching.crew;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    private String course;
    private String name;
    private List<String> levelOne = new ArrayList<>();
    private List<String> levelTwo = new ArrayList<>();
    private List<String> levelFour = new ArrayList<>();


    public Crew(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getCourse(){
        return course;
    }

    public void addLevelOne(String name){
        levelOne.add(name);
    }

    public void addLevelTwo(String name){
        levelTwo.add(name);
    }

    public void addLevelFour(String name){
        levelFour.add(name);
    }

    public boolean isAlreadyMatched(String name, String level){
        if(level.equals("레벨1")){
            return levelOne.contains(name);
        }
        if(level.equals("레벨2")){
            return levelTwo.contains(name);
        }
        if(level.equals("레벨4")){
            return levelFour.contains(name);
        }
        return false;
    }

}

