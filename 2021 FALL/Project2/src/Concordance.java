import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

///////////////////////////////////////////////////////////////////////////////////////////
// This program reads a text file, and then displays each identifier, the number of times
// it occurs, and the line number(s) of each occurrence.
///////////////////////////////////////////////////////////////////////////////////////////

public class Concordance {

    // This TreeMap holds the concordance. Words from the file are used as keys in the map.
    // The value associated with each word is a set that contains the line numbers (integers)
    // on which the word occurs in the file.

    private static TreeMap<String, TreeSet<Integer>>  concordance;



    public static void main(String[] args) {
        FileProcessing.wordCount = 0;             // Currently, there are no words in the array.


        System.out.println("\nThis program will ask you to select an input file.");
        System.out.println("It makes a concordance list for the file.");
        System.out.println("After reading the input file, the program asks you to");
        System.out.println("select an output file to save the list in.\n\n");

        try {

            // Let user select the input file.  If the user cancels, the program ends.

            if (!FileProcessing.readUserSelectedFile()) {
                System.out.println("No input file selected.  Exiting.");
                System.exit(0);
            }

            // Create the TreeMap
            concordance = new TreeMap<String, TreeSet<Integer>>();

            int lineNum = 1;  // The line number for the input file

            while (true) {
                char ch = FileProcessing.peek(); // Look ahead at next character
                while ( ch != FileProcessing.EOF && ! Character.isLetter(ch) ) {
                    // Skips non-letter characters, stops when a letter is seen or end-of-file reach.
                    FileProcessing.getAnyChar();  // Reads the next character.
                    if (ch == '\n') {
                        lineNum++;  // Start a new line.
                    }
                    ch = FileProcessing.peek();  // Look at the next character.
                }
                if (ch == FileProcessing.EOF) {
                    // The end-of-file, exit from the loop.
                    break;
                }
                String word = readWord();  // The next word from the file.
                word = word.toLowerCase();
                if (word.length() > 2  && !word.equalsIgnoreCase("the")) {
                    // Add the reference to word to the concordance, unless
                    // the word is "the" or the word has length <= 2.
                    addReference(word,lineNum);
                    FileProcessing.insertWord(word);
                }
            }

            // Empty file message
            if (concordance.size() == 0) {
                System.out.println("No words found in file.");
                System.out.println("Exiting without saving data.");
                System.exit(0);
            }

            FileProcessing.writeUserSelectedFile(); // If user cancels, output goes to standard output.

            printConcordance();  // Print the data to the output file.

        }
        catch (IllegalArgumentException e) {
            System.out.println( "Sorry, some error occurred:  " + e.getMessage() );
        }

        System.out.println("The concordance from Alice In Wonderland is listed below...");
        displayConcordance(concordance);

    } // end main()


    //////////////////////////////////////////////////////////////////////////////////////////////
    // Writes the data in the concordance to output file, if one has been selected;
    // otherwise, it will go to standard output.  Each line of output contains one word from the
    // file, in alphabetical order, and a list of lines on which that word occurred.
    // Utilizes helper functions found in FileProcessing class.
    //////////////////////////////////////////////////////////////////////////////////////////////
    private static void printConcordance() {

        for ( Map.Entry<String, TreeSet<Integer>>  entry :  concordance.entrySet() ) {

            String term = entry.getKey();
            TreeSet<Integer> pageSet = entry.getValue();

            int index = 0;
            for (int k = 0; k < FileProcessing.words.size(); k++){
                if (FileProcessing.words.get(k).word == term){
                    index = k;
                    break;
                }
            }
            //int occurrences = FileProcessing.words.get(index).count;
            int occurrences = pageSet.size();

            FileProcessing.put( term + " occurs " + occurrences + " times on lines : " );

            for ( int page : pageSet ) {
                FileProcessing.put( page + " " );
            }

            FileProcessing.putln();

        }
    } // end printConcordance

    //////////////////////////////////////////////////////////////////////////////////////////////
    // Writes the data in the concordance to standard output  Each line of output contains one
    // word from the file, in alphabetical order, and a list of lines on which that word occurred.
    // Utilizes helper functions found in FileProcessing class.
    //////////////////////////////////////////////////////////////////////////////////////////////
    public static void displayConcordance(
            TreeMap<String, TreeSet<Integer>> concordance) {
        // printing out all values
        for (Map.Entry<String, TreeSet<Integer>> entry : concordance.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            int index = 0;
            for (int k = 0; k < FileProcessing.words.size(); k++){
                if (FileProcessing.words.get(k).word == key){
                    index = k;
                    break;
                }
            }
            //int occurrences = FileProcessing.words.get(index).count;
            int occurrences = value.length();

            System.out.printf("%s occurs %d times on lines:  %s\n", key, occurrences, value);
        }
    } // end display concordance

    //////////////////////////////////////////////////////////////////////////////////////////////
    // Creates a new word reference set, if this word had not been found yet.
    // Adds a word reference (the line number) to the concordance.
    //////////////////////////////////////////////////////////////////////////////////////////////
    private static void addReference(String word, int lineNum) {
        TreeSet<Integer> references; // The set of lines for previously found words
        references = concordance.get(word);


        if (references == null){
            // Makes a new set containing the line number and adds it to the concordance, with
            // the word as the key.
            TreeSet<Integer> firstRef = new TreeSet<Integer>();
            firstRef.add( lineNum );
            concordance.put(word,firstRef);

        }
        else {
            //Adds the new line number to the set for this word.
            references.add( lineNum );

        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    //  Reads the next word. Accounts for words containing apostrophes.
    //////////////////////////////////////////////////////////////////////////////////////////////

    private static String readWord() {
        char ch = FileProcessing.peek(); // Look at next character in input.
        assert Character.isLetter(ch);
        String word = "";  // This will be the word that is read.
        while (true) {
            word += FileProcessing.getAnyChar();  // Append the letter onto word.
            ch = FileProcessing.peek();  // Look at next character.
            if ( ch == '\'' ) {
                // The next character is an apostrophe.  Read it, and
                // if the following character is a letter, add both the
                // apostrophe and the letter onto the word and continue
                // reading the word.  If the character after the apostrophe
                // is not a letter, the word is done, so break out of the loop.
                FileProcessing.getAnyChar();   // Read the apostrophe.
                ch = FileProcessing.peek();    // Look at char that follows apostrophe.
                if (Character.isLetter(ch)) {
                    word += "\'" + FileProcessing.getAnyChar();
                    ch = FileProcessing.peek();  // Look at next char.
                }
                else
                    break;
            }
            if ( ! Character.isLetter(ch) ) {
                // If the next character is not a letter, the word is
                // finished, so break out of the loop.
                break;
            }
            // If we haven't broken out of the loop, next char is a letter.
        }
        return word;  // Return the word that has been read.
    } // end readWord

    // class for storing the word count and position for each word
    public static class WordCount_Positions {
        private int wordCount = 0;
        private ArrayList<Integer> positions = new ArrayList<Integer>();

        public WordCount_Positions(int startingPosition) {
            wordCount++;
            positions.add(startingPosition);
        }

        public void addToPositions(int newPosition) {
            this.wordCount++;
            this.positions.add(newPosition);

        }

        public int getWordCount() {
            return wordCount;
        }

    } // end Word_Count Positions


} // end class Concordance