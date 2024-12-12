package pairmatching;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        CrewInformationLoader crewInformationLoader = new CrewInformationLoader();
        CrewInformation crewInformation= crewInformationLoader.getCrewInformation();
        List<Crew> allCrewInformation=crewInformation.getAllCrewInformation();
        for(Crew crew:allCrewInformation){
            System.out.println(crew.getName()+crew.getCourse());
        }

    }
}
