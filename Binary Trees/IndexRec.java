public class IndexRec {
    private String value;
    private int index;
    private IndexRec leftChild, rightChild; 

    public IndexRec() { //no parameter constructor
        value = "";
        index = 0;
        leftChild = null;
        rightChild = null;
    }

    //parameter constructor with the index and value
    public IndexRec(String value, int index) {
        //Initializing the String to the parameters
        this.value = value;
        this.index = index;
        leftChild = null;
        rightChild = null;
    }

    //Getter and setter methods for the leftChild and rightChild
    public IndexRec getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(IndexRec leftChild) {
		this.leftChild = leftChild;
	}
	
    public IndexRec getRightChild() {
		return rightChild;
	}

	public void setRightChild(IndexRec rightChild) {
		this.rightChild = rightChild;
	}


	//getValue method to compare two string for the compareto method
    public String getValue() {
        return value;
    }

    //get index to retrieve the student
    //use for unique id check
    public int getIndex() {
        return index;
    }
    
    //compareto method to compare index record objects and
    //get the value of that student to sort them in the index arrays
    public int compareto(IndexRec otherIndex) {
        return value.compareToIgnoreCase(otherIndex.getValue());
    }
    
    public String toString() {
    	return value +" " + index;
    }
}