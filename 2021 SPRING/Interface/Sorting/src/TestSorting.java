import java.util.Arrays;

public class TestSorting {
    public static void main(String[] args){
        int myArray[] = {9, 8, 99, 110, 87, 3, 13, 87, 12};
        int [] myArray3 = {10, 9, 8, 7, 6};

        System.out.println("insertion sort");
        System.out.println("unsorted array before insertion sort");
        printArray(myArray3);
        System.out.println();
        System.out.println("for each iteration, new element is inserted into sorted array");
        int[] results = insertionSort(myArray3);
        System.out.println();

        System.out.println();

        System.out.println("merge sort");
        int[] inputArray = {9, 7, 3, 1, 6, 2, 6, 8, 0};

        System.out.println("unsorted array before merge sort");
        printArray(inputArray);

        MergeSort sorter = new MergeSort();
        System.out.println("sorted array after merge sort");
        sorter.sort(inputArray);

        printArray(inputArray);
        System.out.println();

        System.out.println("QuickSort");
        int[] inputArray3 = {12, 81, 74, 43, 1098, 0, 8, 92, 17, 754, 912, 0, 6, 4};
        quickSort(inputArray3, 0, inputArray3.length-1);

        System.out.println(Arrays.toString(inputArray3));
    }// end of main


    // For insertion sort
    // Array is also divided into sorted area and unsorted area
    // For each iteration, insert first element of unsorted area into appropriate position in the sorted area of array
    /////////////////////////////////////////////////////////////////////////////
    // NAME: insertionSort
    // BEHAVIOR: sorts the given array
    // PARAMETERS: array
    // RETURNS: int array
    /////////////////////////////////////////////////////////////////////////////
    public static int[] insertionSort(int [] a){
        for (int i = 0; i < a.length; i++){
            // select value to be inserted into the sorted area
            // this value is the first element in the unsorted area
            int element = a[i];
            // j variable points to the index position of the last position of the last value in the sorted area
            int j = i - 1;
            // locate hole position for the element to be inserted
            while( j >= 0 && a[j] > element){
                a[j+1] = a[j];
                j--;
            }
            // insert the number at hole position
            a[j+1] = element;
            System.out.print("iteration " + i + ": ");
            printArray(a);
        }
        return a;
    } // end of insertionSort


    // print out the contents of the array
    /////////////////////////////////////////////////////////////////////////////
    // NAME: printArray
    // BEHAVIOR: outputs the array
    // PARAMETERS: array
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public static void printArray(int [] b){
        for (int i = 0; i < b.length; i++){
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }

    // Quick sort is a highly efficient sorting algorithm based on partitioning of array of data into smaller arrays
    // A large array is partitioned into two arrays, any value in the left array smaller that pivot
    // any value in the right array that is bigger than pivot

    /////////////////////////////////////////////////////////////////////////////
    // NAME: sort
    // BEHAVIOR: sorts the given array
    // PARAMETERS: inputArray, start index, end index
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public static void quickSort(int[] inputArray, int start, int end){
        if(start < end){
            // pp is the index position of the correctly placed pivot value in the array
            int pp = partition(inputArray, start, end);
            quickSort(inputArray, start, pp-1); // sorts the left half
            quickSort(inputArray, pp+1, end); // sorts the right half
        }
    } // end of quickSort

    // finds the position of pivot
    // any values less than pivot and any values less than pivot
    /////////////////////////////////////////////////////////////////////////////
    // NAME: partition
    // BEHAVIOR: sorts the given array
    // PARAMETERS: inputArray, start index, end index
    // RETURNS: int
    /////////////////////////////////////////////////////////////////////////////
    public static int partition(int[] inputArray, int start, int end){
        // pivot is the last index in the array
        int pivot = inputArray[end];
        // in the first iteration, i starts from -1
        int i = start -1;

        for(int j = start; j <= end-1; j++){
            // while value at j is greater than pivot, move j to right
            // if value of j is less than pivot, swap value of i with j and increment i
            if (inputArray[j] <= pivot){
                i++;
                //swapping values
                int ival = inputArray[i];
                int jval = inputArray[j];
                inputArray[i] = jval;
                inputArray[j] = ival;
            }
        }
        // at the end of this loop, any value at the position less than i is less than pivot
        // likewise, any position greater than i is greater than pivot
        // the value at i+1 is pivot

        // put the pivot value in the correct slot
        int ival = inputArray[i+1];
        inputArray[end] = ival;
        inputArray[i+1] = pivot;

        // return the position of pivot
        return i+1;
    } // end of partition

} // end of class
