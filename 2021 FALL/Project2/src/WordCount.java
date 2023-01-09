
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Comparator;

/**
 *  This program will read words from an input file, and count the
 *  number of occurrences of each word.  The word data is written to
 *  an output file twice, once with the words in alphabetical order
 *  and once with the words ordered by number of occurrences.  The
 *  user specifies the input file and the output file.
 *
 *  The program demonstrates several parts of Java's framework for
 *  generic programming:  TreeMap, List sorting, Comparators, etc.
 */
public class WordCount {


    /**
     * Represents the data we need about a word:  the word and
     * the number of times it has been encountered.
     */
    private static class WordData {
        String word;
        int count;
        WordData(String w) {
            // Constructor for creating a WordData object when
            // we encounter a new word.
            word = w;
            count = 1;  // The initial value of count is 1.
        }
    } // end class WordData


    /**
     * A comparator for comparing objects of type WordData according to
     * their counts.  This is used for sorting the list of words by frequency.
     */
    private static class CountCompare implements Comparator<WordData> {
        public int compare(WordData data1, WordData data2) {
            return data2.count - data1.count;
            // The return value is positive if data2.count > data1.count.
            // I.E., data1 comes after data2 in the ordering if there
            // were more occurrences of data2.word than of data1.word.
            // The words are sorted according to decreasing counts.
        }
    } // end class CountCompare


    private static String readNextWord() {
        char ch = FileProcessing.peek(); // Look at next character in input.
        while (ch != FileProcessing.EOF && ! Character.isLetter(ch)) {
            FileProcessing.getAnyChar();  // Read the character.
            ch = FileProcessing.peek();   // Look at the next character.
        }
        if (ch == FileProcessing.EOF) // Encountered end-of-file
            return null;

        String word = "";  // This will be the word that is read.
        while (true) {
            word += FileProcessing.getAnyChar();  // Append the letter onto word.
            ch = FileProcessing.peek();  // Look at next character.
            if ( ch == '\'' ) { // handling apostrophes
                FileProcessing.getAnyChar();   // Read the apostrophe.
                ch = FileProcessing.peek();    // Look at char that follows apostrophe.
                if (Character.isLetter(ch)) {
                    word += "\'" + FileProcessing.getAnyChar();
                    ch = FileProcessing.peek();  // Look at next char
                }
                else
                    break;
            }
            if ( ! Character.isLetter(ch) ) {
                // Exit loop is the next character is not a letter
                break;
            }

        }
        return word;  // Return the word that has been read.
    } // end readNextWord

    public static void main(String[] args) {

        System.out.println("\n\n   This program will ask you to select an input file.");
        System.out.println("It will make a list of all the words that occur in the file");
        System.out.println("along with the number of times that each word occurs.");
        System.out.println("This list will be output twice, first in alphabetical order,");
        System.out.println("then in order of frequency of occurrence with the most");
        System.out.println("common word at the top and the least common at the end.");
        System.out.println("   After reading the input file, the program asks you to");
        System.out.println("select an output file.  If you select a file, the list of");
        System.out.println("words will be written to that file; if you cancel, the list");
        System.out.println("be written to standard output.  All words are converted to");
        System.out.println("lower case.\n\n");


        try {

            if (FileProcessing.readUserSelectedFile() == false) {
                System.out.println("No input file selected.  Exiting.");
                System.exit(1);
            }

            // Create a TreeMap to hold the data.  Read the file and record
            // data in the map about the words that are found in the file.

            TreeMap<String,WordData> words = new TreeMap<String,WordData>();
            String word = readNextWord();
            while (word != null) {
                word = word.toLowerCase();  // convert word to lower case
                WordData data = words.get(word);
                if (data == null)
                    words.put( word, new WordData(word) );
                else
                    data.count++;
                word = readNextWord();
            }

            System.out.println("Number of different words found in file:  "
                    + words.size());
            System.out.println();
            if (words.size() == 0) {
                System.out.println("No words found in file.");
                System.out.println("Exiting without saving data.");
                System.exit(0);
            }

            // Copy the word data into an array list, and sort the list
            // into order of decreasing frequency.

            ArrayList<WordData> wordsByFrequency = new ArrayList<WordData>( words.values() );
            Collections.sort( wordsByFrequency, new CountCompare() );

            // Output the data from the map and from the list.

            FileProcessing.writeUserSelectedFile(); // If user cancels, output automatically
            // goes to standard output.
            FileProcessing.putln(words.size() + " words found in file:\n");
            FileProcessing.putln("List of words in alphabetical order"
                    + " (with counts in parentheses):\n");
            for ( WordData data : words.values() )
                FileProcessing.putln("   " + data.word + " (" + data.count + ")");
            FileProcessing.putln("\n\nList of words by frequency of occurrence:\n");
            for ( WordData data : wordsByFrequency )
                FileProcessing.putln("   " + data.word + " (" + data.count + ")");
            System.out.println("\n\nDone.\n\n");

        }
        catch (Exception e) {
            System.out.println("Sorry, an error has occurred.");
            System.out.println("Error Message:  " + e.getMessage());
            e.printStackTrace();
        }
        System.exit(0);  // Might be necessary, because of use of file dialogs.
    } // end Main


} // end WordCount class

