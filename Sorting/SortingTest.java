public class SortingTest {
	
	public static void  main(String[] args){
		
		// Creates the file of random numbers of size that the user will
		// enter when prompted. The array that is placed in the file 
		// is checked to see if that set of information is already sorted.
		RandomGenerator numberGenerator = new RandomGenerator();
		checkArray(numberGenerator.getArray());
		
		// Preforms a sort with the Insertion Sort Algorithm then the sorted array is check and 
		// that information is placed in a file for review. 
		InsertionSort insertion = new InsertionSort(numberGenerator.getFileName());
		int[] insertArrayCheck = insertion.getArray();
		checkArray(insertArrayCheck);
		
		// Preforms a sort with the Bubble Sort Algorithm then the sorted array is check and 
		// that information is placed in a file for review. 
		BubbleSort bubble = new BubbleSort(numberGenerator.getFileName());
		int[] bubbleArrayCheck = bubble.getArray();
		checkArray(bubbleArrayCheck);

		// Preforms a sort with the Merge Sort Algorithm then the sorted array is check and 
		// that information is placed in a file for review. 
		MergeSort merge = new MergeSort(numberGenerator.getFileName());
		int [] mergeSortCheck = merge.getArray();
		checkArray(mergeSortCheck);

		QuickSort quick = new QuickSort(numberGenerator.getFileName());
		int [] quickSortCheck = quick.getArray();
		checkArray(quickSortCheck);
		
	}


	/*
	 * 
	 * The Check array function take in an array of integers that 
	 * is then check to see if the information is sorted correctly.
	 * If the array is not sorted correctly the function is exited and
	 * the user is notified. Otherwise the function will complete and the
	 * user will be told the array was sorted correctly.
	 * 
	 * @param array - An array of integers to be check if it is sorted correctly. 
	 * 
	 */
	public static void checkArray(int[] array){
		
		for(int i = 1; i < array.length; i++){
			
			if(array[i-1] > array[i]){
				System.out.println("The array is not correctly sorted.\n");
				return;
			}
		}

		System.out.println("The array is sorted correctly.\n");
	}

}
