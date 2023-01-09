/*
*  For this, I've combine parts of two and modified them to suit my needs.
*
*  This code is contributed by Ayush Choudhary
*  https://www.geeksforgeeks.org/quick-sort/
*
* And
* @author Maria Halvarsson
* https://github.com/Mimmiiz/algorithms-and-datastructures/blob/master/sorting-lab/src/QuicksortMedianOfThree.java
*
*/

// Java implementation of QuickSort
public class QuickSort {

        // A utility function to swap two elements
        static void swap(int[] arr, int i, int j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        /* This function takes last element as pivot, places
        the pivot element at its correct position in sorted
        array, and places all smaller (smaller than pivot)
        to left of pivot and all greater elements to right
        of pivot */
        static int partition(int[] arr, int low, int high)
        {

            // pivot
            int pivot = arr[high];

            // Index of smaller element and
            // indicates the right position
            // of pivot found so far
            int i = (low - 1);

            for(int j = low; j <= high - 1; j++)
            {

                // If current element is smaller
                // than the pivot
                if (arr[j] < pivot)
                {

                    // Increment index of
                    // smaller element
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
            return (i + 1);
        }

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

        /* The main function that implements QuickSort
                arr[] --> Array to be sorted,
                low --> Starting index,
                high --> Ending index
        */
        static int[] quickSort(int[] arr, int low, int high)
        {

            int m = medianOfThree(arr, low, (high - low)/2, high);
            swap(arr, low, m);

            int j = partition(arr, low, high);
            quickSort(arr, low, j - 1);
            quickSort(arr, j + 1, high);

            if (low < high)
            {

                // pi is partitioning index, arr[p]
                // is now at right place
                int pi = partition(arr, low, high);

                // Separately sort elements before
                // partition and after partition
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }

            return arr;
        }

    // returns the index of the median of three values array[i], array[j] and array[k]
    private static int medianOfThree(int[] array, int i, int j, int k) {
        if ((array[i] > array[j]) != (array[i] > array[k]))
            return i;
        else if ((array[j] > array[i]) != (array[j] > array[k]))
            return j;
        else
            return k;
    }

}

