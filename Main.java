
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Scanner;

class ObservedPin {
    static Map<String, Set<String>> mapPins = new HashMap<>();

    static {
        mapPins.put("1", Set.of("1", "2", "4"));
        mapPins.put("2", Set.of("1", "2", "3", "5"));
        mapPins.put("3", Set.of("2", "3", "6"));
        mapPins.put("4", Set.of("1", "4", "5", "7"));
        mapPins.put("5", Set.of("2", "5", "4", "6", "8"));
        mapPins.put("6", Set.of("3", "5", "6", "9"));
        mapPins.put("7", Set.of("4", "7", "8"));
        mapPins.put("8", Set.of("5", "7", "8", "0"));
        mapPins.put("9", Set.of("6", "8", "9"));
        mapPins.put("0", Set.of("0", "8"));
    }

    public Set<String> getPINs(String observedPin) {
        for (char c : observedPin.toCharArray()) {
            if (!mapPins.containsKey(c + ""))
                throw new RuntimeException("PIN " + observedPin + " contains invalid character " + c);
        }

        if (observedPin.isEmpty()) {
            return Set.of();
        }
        Set<String> pins1 = mapPins.get(observedPin.charAt(0) + "");
        if (observedPin.length() == 1) {
            return pins1;
        } else {
            return combineSolutions(pins1, getPINs(observedPin.substring(1)));
        }
    }

    public Set<String> combineSolutions(Set<String> pins1, Set<String> pins2) {
        return pins1.stream()
                .flatMap((pin1) ->
                        pins2
                                .stream()
                                .map((pin2) -> pin1 + pin2))
                .collect(Collectors.toSet());
    }
}

public class Main
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String pin = sc.nextLine();
        int num = Integer.parseInt(pin);
        if(num >= 0){
            ObservedPin op = new ObservedPin();
            Set<String> sets = op.getPINs(pin);
            int n = sets.size();
            List<String> list = new ArrayList<String>(n);
            int i=0;
            for(String x : sets){
                list.add(x);
                i++;
            }
            System.out.println("There are : "+i+" possible pins");
            List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
            System.out.println(sortedList);
        }
        else{
            System.out.println("Invalid Input");
            return;
        }
    }
}
