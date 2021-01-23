public class AdvancedSorts {

    private int[] theArray; // ref to array theArray
    private int nElems; // number of data items
    //-----------------------------------------------------------
    public AdvancedSorts(int max) // constructor
    {
        theArray = new int[max]; // create array
        nElems = 0;
    }
    //-----------------------------------------------------------
    public void insert(int value) // put element into array
    {
        theArray[nElems] = value; // insert it
        nElems++; // increment size
    }
    //-----------------------------------------------------------
    public void display() // displays array contents
    {
        for (int j = 0; j < nElems; j++) // for each element,
            System.out.println(theArray[j] ); // display it
    }
    
    boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }
    public int[] getTheArray() {
		return theArray;
	}
	//-----------------------------------------------------------
    public void mergeSort() // called by main()
    { // provides workspace
        int[] workSpace = new int[nElems];
        recMergeSort(workSpace, 0, nElems - 1);
    }

    private void recMergeSort(int[] workSpace, int lowerBound,
        int upperBound) {

        if (lowerBound == upperBound) // if range is 1,
            return; // no use sorting
        else { // find midpoint
            int mid = (lowerBound + upperBound) / 2;
            // sort low half
            recMergeSort(workSpace, lowerBound, mid);
            // sort high half
            recMergeSort(workSpace, mid + 1, upperBound);
            // merge them
            merge(workSpace, lowerBound, mid + 1, upperBound);
        } // end else
    } // end recMergeSort()
    
    private void merge(int[] workSpace, int lowPtr,
        int highPtr, int upperBound) {
        int j = 0; // workspace index
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1; // # of items
        while (lowPtr <= mid && highPtr <= upperBound)
            if (theArray[lowPtr] < theArray[highPtr])
                workSpace[j++] = theArray[lowPtr++];
            else
                workSpace[j++] = theArray[highPtr++];
        while (lowPtr <= mid)
            workSpace[j++] = theArray[lowPtr++];
        while (highPtr <= upperBound)
            workSpace[j++] = theArray[highPtr++];
        for (j = 0; j < n; j++)
            theArray[lowerBound + j] = workSpace[j];
    } // end merge()
  //--------------------------------------------------------------
    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }
    
    private void recQuickSort(int left, int right) {
        if (right - left <= 0) // if size <= 1,
            return; // already sorted
        else // size is 2 or larger
        {
            long pivot = theArray[right]; // rightmost item
            // partition range
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1); // sort left side
            recQuickSort(partition + 1, right); // sort right side
        }
    }
    
    private int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1; // left (after ++)
        int rightPtr = right; // right-1 (after --)
        while (true) { // find bigger item
            while (theArray[++leftPtr] < pivot)
            ; // (nop)
            // find smaller item
            while (rightPtr > 0 && theArray[--rightPtr] > pivot)
            ; // (nop)
            if (leftPtr >= rightPtr) // if pointers cross,
                break; // partition done
            else // not crossed, so
                swap(leftPtr, rightPtr); // swap elements
        } // end while(true)
        swap(leftPtr, right); // restore pivot
        return leftPtr; // return pivot location
    } // end partitionIt()
    
    private void swap(int dex1, int dex2) // swap two elements
    {
    	int temp = theArray[dex1]; // A into temp
    	theArray[dex1] = theArray[dex2]; // B into A
    	theArray[dex2] = temp; // temp into B
    } // end swap(
    //-----------------------------------------------------------
    public void heapSort() 
    { 
  
        // Build heap (rearrange array) 
        for (int i = nElems / 2 - 1; i >= 0; i--) 
            heapify(theArray, nElems, i); 
  
        // One by one extract an element from heap 
        for (int i=nElems-1; i>=0; i--) 
        { 
            // Move current root to end 
            int temp = theArray[0]; 
            theArray[0] = theArray[i]; 
            theArray[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(theArray, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    private void heapify(int arr[], int n, int i) 
    { 
        int largest = i;  // Initialize largest as root 
        int l = 2*i + 1;  // left = 2*i + 1 
        int r = 2*i + 2;  // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
} // end class DArray
////////////////////////////