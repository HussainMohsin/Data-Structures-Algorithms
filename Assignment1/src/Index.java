public class Index {
    private IndexRec[] indexRecords; //array of indexRec class
    private int numofElements;
    private int maxSize;
    private int iterator;

    //constructor with zero parameter
    public Index() {
        maxSize = 100;
        numofElements = 0;
        indexRecords = new IndexRec[maxSize];
    }
    
    //constructor with int size as a parameter
    public Index(int size) {
        maxSize = size; //Initialize maxSize to size
        numofElements = 0;
        indexRecords = new IndexRec[maxSize]; //Initialize the array size to maxSize
    }

    //getter to get the number of the element in the indexArray
    public int getNumofElements() {
        return numofElements;
    }
    
    //getter for the to get the whole indexRec
    public IndexRec[] getIndexRecords() {
    	return indexRecords;
    }

    //insertion loop to insert the index records in alphabetical order
    public void insert(IndexRec newIndex) {
        int j;
        for (j = numofElements - 1; j >= 0; j--) {
        	//use compareto method from index records to compare the 2 strings
            if (indexRecords[j].compareto(newIndex) < 0) 
                break;
            indexRecords[j + 1] = indexRecords[j]; //swap the index Records
        }
      //insert the index Records after the index record at J
        indexRecords[j + 1] = newIndex;  
        numofElements++; //must increment the number of elements so we the next index record doesn't  over write it
    }

    //binary search, to search through the sorted index record array
    public int search(String indexValue) {
        int low, high, mid = 0;

        low = 0;
        high = numofElements;

        while (low <= high) {
            mid = (low + high) / 2;
            //compareTo() to compare two strings
            //used getValue method to get the value of the indexRec
            if (indexRecords[mid].getValue().compareToIgnoreCase(indexValue) == 0)
                break; //if the strings values = 0 break

            if (indexRecords[mid].getValue().compareToIgnoreCase(indexValue) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        //return the index of the string if it found or -1 if the string is not found
        //works if array is empty
        return (indexRecords[mid].getValue().compareToIgnoreCase(indexValue) == 0 ? mid : -1);
    }

    public int delete(String key) {
        int where; //going to be the index
        int k;
      //get the index from the search method
        where = search(key); 
        //if the search method returns the index 
        if (where != -1) {
            //remove the string by overwriting it when the string after it
            for (k = where; k < numofElements-1; k++)
                indexRecords[k] = indexRecords[k+1];
            indexRecords[numofElements]= null;
            numofElements--; //must decrement so we do not print the last elements that didn't get over written
        }
        return (where > 0 ? where : -1);
    }
    
    //method to set the iterator to the beginning
    public void setIteratorBegin() {
    	//if the array is empty return -1 else return 0
        iterator = (numofElements > 0 ? 0 : -1);
    }

    //method to set the iterator to the end
    public void setIteratorEnd() {
    	//if the array is empty return -1 else return number of elements -1
        iterator = (numofElements > 0 ? numofElements - 1 : -1);
    }

    public IndexRec getIterator() {
        return (iterator == -1 ? null : indexRecords[iterator]);
    }

    //method to get the next element after the iterator
    public IndexRec getIteratorNext() {
    	//if the iterator is the same as the array size, return -1 else increment it
        iterator = (iterator == numofElements - 1 ? -1 : iterator + 1);
        
        return getIterator();
    }

    //method to get the next element before the iterator
    public IndexRec getIteratorPrev() {
    	//if the iterator is 0, return -1 else decrement it
        iterator = (iterator == 0 ? -1 : iterator - 1);
        
        return getIterator();
    }

    //used to test where my errors occured
//    //print it to print the index record array
//    public void printIt() {
//        for (int j = 0; j < numofElements; j++)
//            System.out.println(indexRecords[j]);
//    }
}