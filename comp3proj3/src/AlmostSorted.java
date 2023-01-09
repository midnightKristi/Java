import java.util.*;
import java.util.Scanner;
import java.io.*;

public class AlmostSorted {

    public static void main(String[] args) {
        int[] array = new int[800000];

        for(int m = 0; m < 800000; m++){
            array[m] = 1;
        }

        String fileName = "RandomNumber800000.txt";

        // Open the file.
        File file = new File(fileName);
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 800000; i++) {
            // Read the file contents into the array
            while (inputFile.hasNext() && i < array.length) {
                array[i] = inputFile.nextInt();
                i++;
            }
        }
        // Close the file.
        inputFile.close();

        // sort the array
        int[] sortedArray = ShellSort.sort(array);

        // unsort 10% and make files
        int[] almost = new int[800000];
        int value;
        int j;
        int num = 50000; // reset num

        //   Write the almost sorted array to a text file
        for(int i = num; i <= 800000; i *=2){
            // unsort 10%
            for(j = 0; j < i; j++){

                if((j % 10) == 0 && j < i){
                    int jPlusNine = j + 9;
                    value = (sortedArray[jPlusNine]);
                    almost[j] = value;
                }
                else{
                    value = sortedArray[j];
                    almost[j] = value;
                }
            } // end unsort 10%

            int[] arr = new int[i];

            for(int m = 0; m < i; m++){
                value = almost[m];
                arr[m] = value;
            }

            RandomGenerator.makeAlmostSortedTextFile(arr);
            System.out.println("The file AlmostSorted" + i + ".txt was created\n");

        } // outer for loop


    } // end main

} // end AlmostSorted
