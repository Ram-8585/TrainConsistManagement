import java.util.*;
class Bogie {
    String type;
    int capacity;

    Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Bogie{type='" + type + "', capacity=" + capacity + "}";
    }
}

public class TCM {
        public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        List<String> passengerBogies = new ArrayList<>();

        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        System.out.println("Bogies after insertion: " + passengerBogies);

        passengerBogies.remove("AC Chair");
        System.out.println("Bogies after removal: " + passengerBogies);

        boolean hasSleeper = passengerBogies.contains("Sleeper");
        System.out.println("Is Sleeper present? " + hasSleeper);


        Set<String> bogieIDs = new HashSet<>();

        bogieIDs.add("BG101");
        bogieIDs.add("BG102");
        bogieIDs.add("BG101");

        System.out.println("Unique Bogie IDs: " + bogieIDs);


        LinkedList<String> consist = new LinkedList<>();

        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry Car");

        consist.removeFirst();
        consist.removeLast();

        System.out.println("Final Train Consist: " + consist);


        Set<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("Final formation order: " + formation);


        Map<String, Integer> bogieCapacity = new HashMap<>();

        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 40);
        bogieCapacity.put("First Class", 24);

        System.out.println("Bogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " Capacity: " + entry.getValue());


            List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 40));
        bogieList.add(new Bogie("First Class", 24));

        bogieList.sort((b1, b2) -> Integer.compare(b1.capacity, b2.capacity));

        System.out.println("Sorted Bogies by Capacity:");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }


        System.out.println("High Capacity Bogies (Capacity > 50):");
        bogieList.stream()
                 .filter(b -> b.capacity > 50)
                 .forEach(System.out::println);


                 Map<String, List<Bogie>> groupedBogies = bogieList.stream()
                .collect(java.util.stream.Collectors.groupingBy(b -> b.type));

        System.out.println("Bogies Grouped by Type:");
        groupedBogies.forEach((type, list) -> System.out.println(type + ": " + list));




        int totalSeats = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Total Seating Capacity of the Train: " + totalSeats);
        
        String trainID = "TRN-1234";
        String trainIDPattern = "TRN-\\d{4}";
        boolean isTrainIDValid = java.util.regex.Pattern.matches(trainIDPattern, trainID);
        System.out.println("Is Train ID " + trainID + " valid? " + isTrainIDValid);

        String cargoCode = "PET-AB";
        String cargoPattern = "[A-Z]{3}-[A-Z]{2}";
        boolean isCargoValid = java.util.regex.Pattern.matches(cargoPattern, cargoCode);
        System.out.println("Is Cargo Code " + cargoCode + " valid? " + isCargoValid);


        }

    }
}