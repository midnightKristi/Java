/*
    NAME: Kristi LaVigne
    CLASS: CSCI 421 - 01
    SEMESTER: Spring 2022
    DESCRIPTION:
        Assignment 4:
        Use Java to implement an external sort program.
        The input data are stored in a file with name input.txt
        (there are 1 million integers in the file).
        Split the original file into 4 smaller parts to process sorting each part.
        Use merge sort to combine 4 sorted parts to generate the final result.
        Assume your computer’s memory space is not big enough to load the whole file
        and only ¼ of the file can fit in the main memory.
        Write the final sorting result to file result.txt.

    References and Credits:
        https://www.geeksforgeeks.org/external-sorting/
        https://tutorialspoint.dev/algorithm/sorting-algorithms/external-sorting
        https://github.com/wexpect/cracking_the_coding_interview_150/blob/0787f340c65bae4bf5a5b98ede2cb1e3b611e586/11%20Sorting%20and%20Searching/11.4%20ExternalSorting/ExternalSorting/src/externalsorting/BinaryFileBuffer.java
*/

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


class BinaryFileBuffer  {
    public static int BUFFERSIZE = 2048;
    public BufferedReader fbr;
    public File originalfile;
    private String cache;
    private boolean empty;

    public BinaryFileBuffer(File f) throws IOException {
        originalfile = f;
        fbr = new BufferedReader(new FileReader(f), BUFFERSIZE);
        reload();
    }

    public boolean empty() {
        return empty;
    }

    private void reload() throws IOException {
        try {
            if((this.cache = fbr.readLine()) == null){
                empty = true;
                cache = null;
            }
            else{
                empty = false;
            }
        } catch(EOFException oef) {
            empty = true;
            cache = null;
        }
    }

    public void close() throws IOException {
        fbr.close();
    }


    public String peek() {
        if(empty()) return null;
        return cache.toString();
    }
    public String pop() throws IOException {
        String answer = peek();
        reload();
        return answer;
    }

}