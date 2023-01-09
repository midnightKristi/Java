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

import java.util.*;
import java.io.*;

public class ExternalSorting {

    public static void main(String[] args) throws IOException {
        if(args.length<2) {
            System.out.println("Please give the input file name and output file name");
            return;
        }
        String inputfile = args[0];
        String outputfile = args[1];
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String r1, String r2){
                return r1.compareTo(r2);}};
        List<File> l = sortInBatch(new File(inputfile), comparator) ;
        mergeSortedFiles(l, new File(outputfile), comparator);
    } // end Main


    // Divide the file into smaller sections (blocks).
    // Goldilocks zone file size: (not too big and not too small)
    //  No more than 1024 temporary files.
    //  Increase the block if it is smaller that half of the free memory (memory available for use)

    public static long approxOptimalBlockSize(File fileToSort) {
        long fileSize = fileToSort.length();

        final int MAXTEMPFILES = 1024;
        long blockSize = fileSize / MAXTEMPFILES ;
        long freeMemory = Runtime.getRuntime().freeMemory();
        if( blockSize < freeMemory/2)
            blockSize = freeMemory/2;
        else {
            if(blockSize >= freeMemory)
                System.err.println("We expect to run out of memory.");
        }
        return blockSize;
    } // end approxOptimalBlockSize


    // This will load the file by blocks of x rows, then sort them in-memory. and
    // Then write the result to several temporary files that will be merged later.

    public static List<File> sortInBatch(File file, Comparator<String> cmp) throws IOException {
        List<File> files = new ArrayList<File>();
        BufferedReader fbr = new BufferedReader(new FileReader(file));
        long blockSize = approxOptimalBlockSize(file);// in bytes
        try{
            List<String> tempList =  new ArrayList<String>();
            String line = "";
            try {
                while(line != null) {
                    long crntBlockSize = 0;// in bytes
                    while((crntBlockSize < blockSize)
                            &&(   (line = fbr.readLine()) != null) ){ // as long as you have 2MB
                        tempList.add(line);
                        crntBlockSize += line.length(); // 2 + 40; // java uses 16 bits per character + 40 bytes of overhead (estimated)
                    }
                    files.add(sortAndSave(tempList,cmp));
                    tempList.clear();
                }
            } catch(EOFException oef) {
                if(tempList.size()>0) {
                    files.add(sortAndSave(tempList,cmp));
                    tempList.clear();
                }
            }
        } finally {
            fbr.close();
        }
        return files;
    } // end sortInBatch

    // Sort file and save it
    public static File sortAndSave(List<String> tmplist, Comparator<String> cmp) throws IOException  {
        Collections.sort(tmplist,cmp);  //
        File newTempFile = File.createTempFile("sortInBatch", "flatfile");
        newTempFile.deleteOnExit();
        BufferedWriter fbw = new BufferedWriter(new FileWriter(newTempFile));
        try {
            for(String r : tmplist) {
                fbw.write(r);
                fbw.newLine();
            }
        } finally {
            fbw.close();
        }
        return newTempFile;
    } // end sortAndSave

    // This merges the temporary files and returns the number of lines sorted.

    public static int mergeSortedFiles(List<File> files, File outputFile, final Comparator<String> cmp) throws IOException {
        PriorityQueue<BinaryFileBuffer> pq = new PriorityQueue<BinaryFileBuffer>(11,
                new Comparator<BinaryFileBuffer>() {
                    public int compare(BinaryFileBuffer i, BinaryFileBuffer j) {
                        return cmp.compare(i.peek(), j.peek());
                    }
                }
        );
        for (File f : files) {
            BinaryFileBuffer bfb = new BinaryFileBuffer(f);
            pq.add(bfb);
        }
        BufferedWriter fbw = new BufferedWriter(new FileWriter(outputFile));
        int rowcounter = 0;
        try {
            while(pq.size()>0) {
                BinaryFileBuffer bfb = pq.poll();
                String r = bfb.pop();
                fbw.write(r);
                fbw.newLine();
                ++rowcounter;
                if(bfb.empty()) {
                    bfb.fbr.close();
                    bfb.originalfile.delete();// remove unneeded file
                } else {
                    pq.add(bfb); // add it back
                }
            }
        } finally {
            fbw.close();
            for(BinaryFileBuffer bfb : pq ) bfb.close();
        }
        return rowcounter;
    } // end mergeSortedFiles


} // end ExternalSorting
