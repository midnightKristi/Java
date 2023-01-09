// displays some of the properties of the files in the current directory

import java.io.*;

public class ListFiles {
    public static void main(String[] args){
        // Obtain a list of all files in the current directory
        File currentDirectory = new File(".");
        // Returns an array of strings naming the files and directories in the directory
        String[] fileNames = currentDirectory.list();

        // Display each name in the list
        for(int i = 0; i < fileNames.length; i++){
            // Construct a File object for the current file
            File f = new File(fileNames[i]);

            // Test whether the file is a directory and whether it can be read and/or written
            System.out.print(f.isDirectory() ? "d" : " ");
            System.out.print(f.canRead() ? "r" : " ");
            System.out.print(f.canWrite() ? "w" : " ");

            // Display the length of the file and the name of the file
            // The file length and the spaces that precede it will always occupy 11 characters
            String fileLength = f.length() + "";
            final String SPACES = "           ";
            String padding = SPACES.substring(1, 11 - fileLength.length());
            System.out.println(padding + fileLength + fileNames[i]);
        }

    } // end of main

} // end of ListFiles
