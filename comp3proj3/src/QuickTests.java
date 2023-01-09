import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class QuickTests {

    public static void testUnsorted(){
        int n = 3; // the number of time to run each sorting algorithm

        double time = 0; // to store the time run time

        int[] array = new int[800000]; // initializing the array

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

        int fifty = 50000;
        int hundred = 100000;
        int twohund = 200000;
        int fourhun = 400000;

        int[] arr50000 = new int[fifty];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr50000[j] = temp;
        }

        int[] arr100000 = new int [hundred];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr100000[j] = temp;
        }

        int[] arr200000 = new int [twohund];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr200000[j] = temp;
        }

        int[] arr400000 = new int [fourhun];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr400000[j] = temp;
        }


        for(int k = 1; k < 6; k++){
            System.out.println("-- QUICK SORT TIME TEST - UNSORTED ARRAY --");

            Stopwatch stopwatch = new Stopwatch();
            switch (k) {
                case 1:
                    System.out.println("          Array size: " + arr50000.length);
                    InsertionSort.sort(arr50000);
                    break;
                case 2:
                    System.out.println("          Array size: " + arr100000.length);
                    InsertionSort.sort(arr100000);
                    break;
                case 3:
                    System.out.println("          Array size: " + arr200000.length);
                    InsertionSort.sort(arr200000);
                    break;
                case 4:
                    System.out.println("          Array size: " + arr400000.length);
                    InsertionSort.sort(arr400000);
                    break;
                case 5:
                    System.out.println("          Array size: " + array.length);
                    InsertionSort.sort(array);
                    break;

            }
            time += stopwatch.elapsedTime();
            System.out.println("          Sort time: " + time + " ms");


        }

    } //end testUnsorted

    public static void testAlmostSorted(){
        int n = 3; // the number of time to run each sorting algorithm

        double time = 0; // to store the time run time

        int[] array = new int[800000]; // initializing the array

        for(int m = 0; m < 800000; m++){
            array[m] = 1;
        }

        String fileName = "AlmostSorted800000.txt";

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

        int fifty = 50000;
        int hundred = 100000;
        int twohund = 200000;
        int fourhun = 400000;

        int[] arr50000 = new int[fifty];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr50000[j] = temp;
        }

        int[] arr100000 = new int [hundred];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr100000[j] = temp;
        }

        int[] arr200000 = new int [twohund];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr200000[j] = temp;
        }

        int[] arr400000 = new int [fourhun];
        for (int j = 0; j < fifty; j++){
            int temp = array[j];
            arr400000[j] = temp;
        }


        for(int k = 1; k < 6; k++) {
            System.out.println("-- QUICK SORT TIME TEST - UNSORTED ARRAY --");

            Stopwatch stopwatch = new Stopwatch();
            switch (k) {
                case 1:
                    System.out.println("          Array size: " + arr50000.length);
                    InsertionSort.sort(arr50000);
                    break;
                case 2:
                    System.out.println("          Array size: " + arr100000.length);
                    InsertionSort.sort(arr100000);
                    break;
                case 3:
                    System.out.println("          Array size: " + arr200000.length);
                    InsertionSort.sort(arr200000);
                    break;
                case 4:
                    System.out.println("          Array size: " + arr400000.length);
                    InsertionSort.sort(arr400000);
                    break;
                case 5:
                    System.out.println("          Array size: " + array.length);
                    InsertionSort.sort(array);
                    break;

            }
            time += stopwatch.elapsedTime();
            System.out.println("          Sort time: " + time + " ms");

        }
    } //end testAlmostSorted

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Test unsorted or almost? ");
        String choice = scan.nextLine();
        String almost = "almost";
        String unsorted = "unsorted";


        if (choice.equals(unsorted)){
            testUnsorted();
        }
        else if(choice.equals(almost)){
            testAlmostSorted();
        }
        else{
            System.out.println("Sorry, you must type 'unsorted' or 'almost'.");
        }
    } // end main

} //end QuickTests
