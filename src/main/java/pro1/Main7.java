package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main7 {

    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        ActionsList specializations = new Gson().fromJson(json, ActionsList.class);


        return specializations.prijimaciObor.stream()
                .map(i -> i.eprDeadlinePrihlaska.value)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparing((String date) -> {
                    String[] parts = date.split("\\.");
                    return Integer.parseInt(parts[2]) * 10000 +
                            Integer.parseInt(parts[1]) * 100 +
                            Integer.parseInt(parts[0]);
                }))
               // .peek(System.out::println)



                .collect(Collectors.joining(","));
    }
}