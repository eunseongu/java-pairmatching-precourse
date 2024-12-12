package pairmatching;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import pairmatching.crew.Crew;
import pairmatching.crew.CrewInformation;

public class CrewInformationLoader {
    CrewInformation crewInformation = new CrewInformation();

    public CrewInformationLoader() {
        loadBackendCrewInformation();
        loadFrontendCrewInformation();
    }

    public CrewInformation getCrewInformation() {
        return crewInformation;
    }

    private void loadBackendCrewInformation() {
        String filePath = "src/main/resources/backend-crew.md";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                addCrewToCrewInformation(line, "backend");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("파일을 불러오는 데 문제가 발생했습니다. 잠시 후 다시 시도해 주세요.");
        }
    }

    private void loadFrontendCrewInformation() {
        String filePath = "src/main/resources/frontend-crew.md";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                addCrewToCrewInformation(line, "frontend");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("파일을 불러오는 데 문제가 발생했습니다. 잠시 후 다시 시도해 주세요.");
        }
    }

    private void addCrewToCrewInformation(String name, String course) {
        if (name.length()!=0) {
            Crew crew =new Crew(name,course);
            if (crew != null) {
                crewInformation.addCrew(crew);
            }
        }
    }
}
