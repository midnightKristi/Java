import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConcordanceTreeSet {
    static int current_index = 0;
    // stores the number of occurrences of each identifier
    private static TreeMap<String, Integer> frequency;
    // stores the line numbers containing the identifiers
    // list of positions, each index stores a treeset. Each treeset corresponds to a separate identifier
    static List<TreeSet<Integer>> positions;
    // stores the index of each identifier in the positions array
    private static TreeMap<String, Integer> index;

    private static boolean checkIdentifier(String data) {
        if (data.isEmpty()) return false;
        // 1st character is not a letter
        if (!Character.isLetter(data.charAt(0))) return false;
        for (int i = 0; i < data.length(); i++) {
            // current character is neither a number nor an alphabet
            if (!Character.isDigit(data.charAt(i)) && !Character.isAlphabetic(data.charAt(i))) return false;
        }
        return true;
    }

    // processes each line in text and helps in creating the map and set
    private static void process(String data, int line_number) {
        String[] splited = data.split("\\s+");
        for (var x : splited) {
            if (checkIdentifier(x)) {
                int count = frequency.getOrDefault(x, 0);
                frequency.put(x, count + 1);
                if (count == 0) {
                    // if current identifier is encountered for the 1st time then add another
                    // treeset for this identifier in the positions arras
                    positions.add(new TreeSet<>());
                    positions.get(current_index).add(line_number);
                    index.put(x, current_index);
                    current_index++;
                }
                else{
                    positions.get(index.get(x)).add(line_number);
                }
            }
        }
    }

    // reads each line in text and then calls helper function for processing the data
    private static void extractIdentifiers(String filepath) {
        int line_number = 1;
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                process(data, line_number);
                line_number++;
            }
            myReader.close();
            output();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // prints output in the format mentioned in the question
    public static void output() {
        for (var x : frequency.entrySet()) {
            System.out.print(x.getKey() + " occurs " + x.getValue() + " times on lines ");
            var idx = index.get(x.getKey());
            for (var pos : positions.get(idx)) {
                System.out.print(pos + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        // assumes that the input file is in the same folder
        String filepath = "alice_in_wonderland.txt";
        // initialize map and set
        frequency = new TreeMap<>();
        positions = new ArrayList<>();
        index = new TreeMap<>();
        extractIdentifiers(filepath);
    }
}
