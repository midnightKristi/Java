import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

//
// This program makes a concordance for a file.  A concordance
// lists all the words that occur in the file, along with all
// the line numbers on which each word occurs.  (Words of
// length less than 3 are omitted, and "the" is omitted.)  The
// concordance is written to an output file.  The user selects
//the input and output files using file dialog boxes.  This
//program uses the non-standard class, TextIO.
//
public class Concordance {

 //
 // This TreeMap holds the concordance.  Words from the file
 // are used as keys in the map.  The value associated with
 // each word is a set that contains the line numbers on which
 // the word occurs in the file.  The set contains integer values
 //
    private static TreeMap<String, TreeSet<Integer>>  concordance;

    public static void main(String[] args) {

        System.out.println("\nThis program will ask you to select an input file.");
        System.out.println("It makes a concordance list for the file.");
        System.out.println("After reading the input file, the program asks you to");
        System.out.println("select an output file to save the list in.\n\n");

        try {

            // Let user select the input file.  If the user cancels,
            // the program ends immediately.

            if (FileProcessing.readUserSelectedFile() == false) {
                System.out.println("No input file selected.  Exiting.");
                System.exit(0);
            }

            // Create the data structure that will hold the concordance.

            concordance = new TreeMap<String, TreeSet<Integer>>();

            int lineNum = 1;  // The number of the line in the input
            // file that is currently being processed.

            // Read words from the file until end of file is reached,
            // and add each word to the data.

            while (true) {
                char ch = FileProcessing.peek(); // Look ahead at next character
                while ( ch != FileProcessing.EOF && ! Character.isLetter(ch) ) {
                    // Skip over non-letter characters, stopping when
                    // end-of-file (TextIO.EOF) or a letter is seen.  If the
                    // character is an end-of-line character, add 1
                    // to lineNum to reflect the fact that we are moving
                    // on to the next line in the file.
                    FileProcessing.getAnyChar();  // Reads the next character. (junk)
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
                }
            }

            // Write the data to a user-selected file, or to standard
            // output if the user does not select an output file.

            System.out.println(concordance.size() + " distinct words were found in the file.\n");
            System.out.println();
            if (concordance.size() == 0) {
                System.out.println("No words found in file.");
                System.out.println("Exiting without saving data.");
                System.exit(0);
            }

            FileProcessing.writeUserSelectedFile(); // If user cancels, output automatically
            // goes to standard output.

            printConcordance();  // Print the data to the output file.

        }
        catch (IllegalArgumentException e) {
            System.out.println( "Sorry, some error occurred:  " + e.getMessage() );
        }

        System.out.println("The concordance from Alice In Wonderland is listed below...");
        displayConcordance(concordance);

    } // end main()


    //
    // Writes the data in the concordance to TextIO.  (The output will go
    // to the output file, if one has been selected; otherwise, it will go
    // to standard output.)  Each line of output contains one word from the
    // file and a list of lines on which that word occurred.  The words
    // are in alphabetical order.
    //
    private static void printConcordance() {

        for ( Map.Entry<String, TreeSet<Integer>>  entry :  concordance.entrySet() ) {

            String term = entry.getKey();
            TreeSet<Integer> pageSet = entry.getValue();

            TextIO.put( term + " " );
            for ( int page : pageSet ) {
                TextIO.put( page + " " );
            }
            TextIO.putln();

        }
    }

    // displaying Concordance
    public static void displayConcordance(
            TreeMap<String, TreeSet<Integer>> concordance) {
        // printing out all values
        for (Map.Entry<String, TreeSet<Integer>> entry : concordance.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            System.out.printf("%s  %s\n", key, value);
        }
    } // end display concordance


     // Add a word reference to the concordance.
    private static void addReference(String word, int lineNum) {
        TreeSet<Integer> references; // The set of lines where we have
        //    previously found the word.
        references = concordance.get(word);
        if (references == null){
            // This is the first reference that we have
            // found for the word.  Make a new set containing
            // the line number and add it to the concordance, with
            // the word as the key.
            TreeSet<Integer> firstRef = new TreeSet<Integer>();
            firstRef.add( lineNum );  // lineNum is "autoboxed" to give an Integer!
            concordance.put(word,firstRef);
        }
        else {
            // The variable references is the set of line references
            // that we have found previously for the word.
            // Add the new line number to that set.  This
            // set is already associated to word in the concordance.
            references.add( lineNum ); // pageNum is "autoboxed" to give an Integer!
        }
    }


     //
     //  Read the next word from TextIO.  It is assumed that the next character
     // in input is a letter.
     //   A word is defined as a sequence of letters.  Also, a word can include
     //  an apostrophe if the apostrophe is surrounded by letters on each side.
     // @return the next word from TextIO, or null if an end-of-file is encountered
     //

    private static String readWord() {
        char ch = TextIO.peek(); // Look at next character in input.
        assert Character.isLetter(ch);
        String word = "";  // This will be the word that is read.
        while (true) {
            word += TextIO.getAnyChar();  // Append the letter onto word.
            ch = TextIO.peek();  // Look at next character.
            if ( ch == '\'' ) {
                // The next character is an apostrophe.  Read it, and
                // if the following character is a letter, add both the
                // apostrophe and the letter onto the word and continue
                // reading the word.  If the character after the apostrophe
                // is not a letter, the word is done, so break out of the loop.
                TextIO.getAnyChar();   // Read the apostrophe.
                ch = TextIO.peek();    // Look at char that follows apostrophe.
                if (Character.isLetter(ch)) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();  // Look at next char.
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
    }


} // end class Concordance