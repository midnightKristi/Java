// AnagramVector
// This program will provide anagrams to the given words and determine if two words are anagrams

import java.util.*;

public class AnagramVector {
    private static Vector words = new Vector();
    // signature for each word consists of the letters in the word after they have been sorted
    private static Vector signatures = new Vector();

    public static void main(String[] args){
        readWords();
        printAnagrams();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: readWords
    //BEHAVIOR: Repeatedly prompts the user to enter words, stopping when the user enters an empty word. Computes
    //          the signature of each word, storing the signature in the signatures vector in such a way that the
    //          vector is always sorted in ascending order. Stores each word in the words vector at the same
    //          position as the signature in the signatures vector.
    //PARAMETERS: none
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void readWords(){
        Scanner scan = new Scanner(System.in);

        while(true){
            // Prompt user for a word
            System.out.println("Enter a word: ");
            String word = scan.next().trim();

            // Terminate program if user enters "q"
            if( word.equalsIgnoreCase("q")){
                return;
            }
            // Terminate program if the word is empty
            if(word.length() == 0){
                return;
            }
            // Compute the signature of the word
            String signature = computeSignature(word);

            // Determine where the signature belongs in the signatures vector
            // make sure signature vector is always sorted in ascending order
            int i;
            for(i = 0; i < signatures.size(); i++){
                String signatureInVector = (String) signatures.elementAt(i);

                if(signature.compareTo(signatureInVector) < 0){
                    break;
                }
            }

            // Insert signature into signatures vector
            // Insert the word at the corresponding location in the words vector
            signatures.insertElementAt(signature, i);
            words.insertElementAt(word, i);

        } // end of while loop

    } // end of readWords

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: printAnagrams
    //BEHAVIOR: Prints the contents of the words vector, putting words that are anagrams of each other on the
    //          same line. Uses the signatures vector to determine which words are anagrams. Assumes that the
    //          i-th element of the signatures vector are is the signature of the i-th word in the words vector.
    //          Assumes that the elements of the signatures vector are in sorted order.
    //PARAMETERS: none
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void printAnagrams() {
        int anagramNumber = 1;
        int signatureCount = signatures.size();

        if (signatureCount == 0) {
            return;
        }

        //heading and first word fron the word vector
        System.out.println("\n Anagrams:\n-----------");
        System.out.print("1. " + words.firstElement());

        // print remaining words
        for (int i = 1; i < signatureCount; i++) {
            if (signatures.elementAt(i).equals(signatures.elementAt(i - 1)))
                // placing on same line if they have the sme signature
                System.out.print(" ");
            else
                // new line for new word
                System.out.print("\n" + ++anagramNumber + ". ");
            System.out.print(words.elementAt(i));
        }

       System.out.println();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: computeSignature
    //BEHAVIOR: Compute the signature of a word by sorting the letters in the word. Characters other than letters
    //          are ignored, and uppercase letters are converted to lowercase.
    //PARAMETERS: word - the word whose signature is to be computed
    //RETURNS: String object containing the same letters as the original word, but in sorted order
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static String computeSignature(String word){
        // create a new string buffer
        StringBuffer stringBuf = new StringBuffer(word.length());

        for(int i = 0; i < word.length(); i++){
            // extract the character at position i in word
            // if the character is not a letter, ignore it
            // otherwise, convert it to lowercase
            char ch = word.charAt(i);
            if (!Character.isLetter(ch))
                continue;
            ch = Character.toLowerCase(ch);

            // Insert the character into the string buffer, keeping the characters in the buffer sorted constantly
            int j;
            for(j = 0; j < stringBuf.length(); j++)
                if(ch < stringBuf.charAt(j))
                    break;
            stringBuf.insert(j, ch);
        }
        // Convert the string buffer into a string and return it
        return stringBuf.toString();
    } // end of computeSignature

} // end of class
