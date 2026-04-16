import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

// 1. Define the custom exception
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// 2. Define the Bogie class (ONLY ONCE)
class Bogie {
    String type;
    int capacity;

    // Constructor with Checked Exception
    Bogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero.");
        }
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Bogie{type='" + type + "', capacity=" + capacity + "}";
    }
}

// 3. Define the GoodsBogie class
class GoodsBogie {
    String shape;
    String cargo;

    GoodsBogie(String shape, String cargo) {
        this.shape = shape;
        this.cargo = cargo;
    }
}

class CargoSafetyException extends Exception {
    public CargoSafetyException(String message) {
        super(message);
    }
}

public class TCM {
    public static void main(String[] args) {
        // We wrap the whole logic in a try-catch because UC7-UC13 use the Bogie constructor
        try {
            System.out.println("=== Train Consist Management App ===");

            // UC1: ArrayList Basics
            List<String> passengerBogies = new ArrayList<>();
            passengerBogies.add("Sleeper");
            passengerBogies.add("AC Chair");
            passengerBogies.add("First Class");
            passengerBogies.remove("AC Chair");
            System.out.println("UC1 - Bogies: " + passengerBogies);

            // UC2: HashSet (Unique IDs)
            Set<String> bogieIDs = new HashSet<>();
            bogieIDs.add("BG101");
            bogieIDs.add("BG102");
            bogieIDs.add("BG101");
            System.out.println("UC2 - Unique IDs: " + bogieIDs);

            // UC3: LinkedList (Formation)
            LinkedList<String> consist = new LinkedList<>();
            consist.add("Engine");
            consist.add("Sleeper");
            consist.add(1, "Pantry Car");
            System.out.println("UC3 - Consist: " + consist);

            // UC4: LinkedHashSet (Order)
            Set<String> formation = new LinkedHashSet<>();
            formation.add("Engine");
            formation.add("Sleeper");
            formation.add("Cargo");
            System.out.println("UC4 - Formation: " + formation);

            // UC5 & 6: HashMap (Capacity)
            Map<String, Integer> bogieCapacity = new HashMap<>();
            bogieCapacity.put("Sleeper", 72);
            bogieCapacity.put("AC Chair", 40);
            System.out.println("UC5/6 - Capacity Map: " + bogieCapacity);

            // UC7: Sorting Objects
            List<Bogie> bogieList = new ArrayList<>();
            bogieList.add(new Bogie("Sleeper", 72));
            bogieList.add(new Bogie("AC Chair", 40));
            bogieList.add(new Bogie("First Class", 24));

            bogieList.sort((b1, b2) -> Integer.compare(b1.capacity, b2.capacity));
            System.out.println("\nUC7 - Sorted Bogies:");
            bogieList.forEach(System.out::println);

            // UC8 & 9: Streams (Filter & Grouping)
            System.out.println("\nUC8 - Capacity > 50:");
            bogieList.stream().filter(b -> b.capacity > 50).forEach(System.out::println);

            Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.type));
            System.out.println("UC9 - Grouped: " + grouped);

            // UC10: Reduce
            int totalSeats = bogieList.stream().map(b -> b.capacity).reduce(0, Integer::sum);
            System.out.println("UC10 - Total Seats: " + totalSeats);

            // UC11: Regex
            System.out.println("UC11 - ID Valid: " + Pattern.matches("TRN-\\d{4}", "TRN-1234"));

            // UC12: Safety Match
            List<GoodsBogie> goodsBogies = new ArrayList<>();
            goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
            boolean isSafe = goodsBogies.stream()
                .filter(gb -> gb.shape.equals("Cylindrical"))
                .allMatch(gb -> gb.cargo.equals("Petroleum"));
            System.out.println("UC12 - Safety Compliant: " + isSafe);

            // UC13: Performance
            System.out.println("\n--- UC13: Performance Comparison ---");
            long startLoop = System.nanoTime();
            for (Bogie b : bogieList) { if (b.capacity > 60) {} }
            long endLoop = System.nanoTime();
            System.out.println("Loop Time: " + (endLoop - startLoop) + " ns");

            long startStream = System.nanoTime();
            bogieList.stream().filter(b -> b.capacity > 60).collect(Collectors.toList());
            long endStream = System.nanoTime();
            System.out.println("Stream Time: " + (endStream - startStream) + " ns");

            // UC14: Custom Exception Handling
            System.out.println("\n--- UC14: Custom Exception Handling ---");
            System.out.println("Attempting to create an invalid bogie...");
            Bogie invalidBogie = new Bogie("Emergency", -10); 

        } catch (InvalidCapacityException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());

            // UC15: Safe Cargo Assignment (try-catch-finally)
        System.out.println("\n--- UC15: Safe Cargo Assignment ---");
        String assignedShape = "Rectangular";
        String assignedCargo = "Petroleum";

        try {
            System.out.println("Validating cargo: " + assignedCargo + " for " + assignedShape + " bogie...");
            
            // Safety Rule: Petroleum MUST be in Cylindrical bogies
            if (assignedShape.equals("Rectangular") && assignedCargo.equals("Petroleum")) {
                throw new CargoSafetyException("CRITICAL ERROR: Petroleum leakage risk in Rectangular bogie!");
            }
            System.out.println("Cargo assigned successfully.");
            
        } catch (CargoSafetyException e1) {
            System.out.println("Safety Violation: " + e1.getMessage());
        } finally {
            // This always runs
            System.out.println("Cleanup: Safety inspection log updated and sensors reset.");
        }
        // UC16: Bubble Sort - Manual Sorting Logic
        System.out.println("\n--- UC16: Manual Sorting (Bubble Sort) ---");
        int[] capacities = {72, 56, 24, 70, 60};
        int n = capacities.length;

        System.out.println("Original Capacities: " + Arrays.toString(capacities));

        // Bubble Sort Algorithm
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    // Swap elements
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted Capacities (Bubble Sort): " + Arrays.toString(capacities));



        // UC17: Using built-in Arrays.sort() for Bogie Names
        System.out.println("\n--- UC17: Built-in Sorting (Arrays.sort) ---");
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        
        System.out.println("Original Names: " + Arrays.toString(bogieNames));

        // Sorting alphabetically
        Arrays.sort(bogieNames);

        System.out.println("Sorted Names (Alphabetical): " + Arrays.toString(bogieNames));

        // UC18: Linear Search for a Bogie ID
        System.out.println("\n--- UC18: Linear Search ---");
        String[] bogieIDs = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        String searchKey = "BG309";
        boolean found = false;

        for (int i = 0; i < bogieIDs.length; i++) {
            if (bogieIDs[i].equals(searchKey)) {
                found = true;
                System.out.println("Bogie " + searchKey + " found at index: " + i);
                break; // Stop searching once found
            }
        }

        if (!found) {
            System.out.println("Bogie " + searchKey + " not found in the consist.");
        }


        // UC19: Binary Search (Requires Sorted Data)
        System.out.println("\n--- UC19: Binary Search (Optimized) ---");
        
        // Step 1: Data must be sorted for Binary Search to work
        String[] sortedBogieIDs = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        String targetID = "BG412";
        
        int low = 0;
        int high = sortedBogieIDs.length - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Compare strings lexicographically
            int comparison = targetID.compareTo(sortedBogieIDs[mid]);

            if (comparison == 0) {
                resultIndex = mid;
                break; // Found it!
            } else if (comparison > 0) {
                low = mid + 1; // Look in the right half
            } else {
                high = mid - 1; // Look in the left half
            }
        }

        if (resultIndex != -1) {
            System.out.println("Bogie " + targetID + " found at index: " + resultIndex + " (via Binary Search)");
        } else {
            System.out.println("Bogie " + targetID + " not found.");
        }
        
        }
    }
}