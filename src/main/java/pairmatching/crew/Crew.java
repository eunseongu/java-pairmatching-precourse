package pairmatching.crew;

public class Crew {
    private String course;
    private String name;


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

}

