// This program sorts the characters in a string

// A string buffer is like a String, but can be modified. String is immutable.
// At any point in time it contains some particular sequence of characters,
// but the length and content of the sequence can be changed through certain method calls

import java.util.*;

public class InsertionSortChar {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Prompt for a string to be entered by the user
        System.out.println("Enter a string: ");
        String userInput = scan.nextLine();

        // Create a string buffer to hold the sorted string
        StringBuffer stringBuf = new StringBuffer();

        // Insert characters from the user's input into the string buffer,
        // keeping the characters in the buffer sorted at all times
        for (int i = 0; i < userInput.length(); i++){
            char ch = userInput.charAt(i);
            int j;
            for (j = 0; j < stringBuf.length(); j++){
                if ( ch < stringBuf.charAt(j))
                    break;
            }
            stringBuf.insert(j, ch);
        }

        // Display the contents of the string buffer
        System.out.println("Sorted version of string: " + stringBuf);

    } // end of main

} // end of InsertionSortChar
