// Merge sort is a sorting technique based on divide and conquer
// Merger sort first divides the array into equal halves and them combines them in a sorted manner
//      step 1: if there is only one element in the array then it is already sorted
//      step 2: divide the list recursively into two halves until it can no longer be divided
//      step 3: merge the smaller arrays into new array in sorted order

public class MergeSort {
    /////////////////////////////////////////////////////////////////////////////
    // NAME: sort
    // BEHAVIOR: sorts the given array
    // PARAMETERS: inputArray, start index, end index
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public static void sort(int inputArray[]){
        sort(inputArray, 0, inputArray.length-1);
    } // end of sort

    // start is the first index for inputArray
    // end is the last index for inputArray
    /////////////////////////////////////////////////////////////////////////////
    // NAME: sort
    // BEHAVIOR: sorts the given array
    // PARAMETERS: inputArray, start index, end index
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public static void sort(int inputArray[], int start, int end){
        // traversing the array
        if(end <= start){
            return;
        }

        int mid = (start + end)/2; // the middle index
        sort(inputArray, start, mid); // sort left half of the array
        sort(inputArray, mid+1, end); // sort right half of the array
        merge(inputArray, start, mid, end); // merge sorted results into the inputArray
    }

    // start is the first index, end if the last index, and mid is the middle index for inputArray
    // merge function will merge sorted array in the left and sorted array in the right into one big sorted array
    /////////////////////////////////////////////////////////////////////////////
    // NAME: merge
    // BEHAVIOR: merges the sorted arrays
    // PARAMETERS: inputArray, start index, end index
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public static void merge(int inputArray[], int start, int mid, int end){
        // create tempArray for storing results
        int tempArray[] = new int [end - start+1];

        // index for first position of the left half of the sorted array
        int leftSlot = start;

        //index for first position of the right half of the sorted array
        int rightSlot = mid +1;

        // k is the index for the temp array
        int k = 0;

        // for each iteration, add the smaller value between leftSlot an rightSlot to the tempArray
        // then increase index k of the tempArray
        // the loop stops when it reaches either the end of the left half or end of the right half
        while( leftSlot <= mid && rightSlot <= end){
            if(inputArray[leftSlot] < inputArray[rightSlot]){
                tempArray[k] = inputArray[leftSlot];
                leftSlot = leftSlot + 1;
            }
            else{
                tempArray[k] = inputArray[rightSlot];
                rightSlot = rightSlot + 1;
            }
            k = k + 1;
        }

        // in this case, right half of array is completely merged into tempArray
        // the rest of the left half will now be added to tempArray
        if(leftSlot <= mid){
            while(leftSlot <= mid){
                tempArray[k] = inputArray[leftSlot];
                leftSlot = leftSlot + 1;
                k = k + 1;
            }
            // in this case, left half of array is completely merged into tempArray
            // the rest of the right half will now be added to tempArray
        } else if( rightSlot <= end){
            while(rightSlot <= end){
                tempArray[k] = inputArray[rightSlot];
                rightSlot = rightSlot + 1;
                k = k + 1;
            }
        }

        // copy over the temp array into the appropriate slots of the inputArray
        for (int i = 0; i < tempArray.length; i++){
            inputArray[start+i] = tempArray[i];
        }

    } // end of merge

} // end of class
