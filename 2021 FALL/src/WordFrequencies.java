public class WordFrequencies {

    public class WordData{
        String word;
        int count;     // The number of times the word has been found.
        WordData(String w) {
            // Constructor creates an object with the specified word
            // and with the counter initialized to 1.
            word = w;
            count = 1;
        }
    }

   public WordData[] words;  // An array to hold the words from the file.
        //  will be expanded as needed, in the insertWord() subroutine.

    public int wordCount =0 ;   // The number of words currently stored in


    static void insertWord(String w) {
        // Insert the word w into the array of words, unless it already
        // appears there.  If the word already appears in the list,
        // add 1 to the counter for that word.  The words in the array are in
        // lower case,  and w is converted to lower case before it is processed.
        // Note that the words in the array are kept in alphabetical order.
        // If the array has no more space to hold w, then it is doubled
        // in size.

        int pos = 0;  // This will be the position in the array where w belongs.

        w = w.toLowerCase();

         /* Find the position in the array where w belongs, after all the
            words that precede w alphabetically.  If a copy of w already
            occupies that position, then it is not necessary to insert
            w, so just add 1 to the counter associated with the word
            and return. */

        while (pos < wordCount && words[pos].word.compareTo(w) < 0)
            pos++;
        if (pos < wordCount && words[pos].word.equals(w)) {
            words[pos].count++;
            return;
        }

         /* If the array is full, make a new array that is twice as
             big, copy all the words from the old array to the new,
             and set the variable, words, to refer to the new array. */

        if (wordCount == words.length) {
            WordData[] newWords = new WordData[words.length*2];
            System.arraycopy(words,0,newWords,0,wordCount);
            words = newWords;
        }

         /* Put w into its correct position in the array.  Move any
            words that come after w up one space in the array to
            make room for w.  Create a new WordData object to hold
            the new word.  */

        for (int i = wordCount; i > pos; i--)
            words[i] = words[i-1];
        words[pos] = new WordData(w);
        wordCount++;

    }  // end insertWord()

}// end WordFrequencies