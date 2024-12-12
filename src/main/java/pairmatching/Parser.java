package pairmatching;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    // 문자열을 구분자로 나눠 리스트로 변환
    public static List<String> parseList(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}

