public class IndexRec {
    private String value;
    private int index;
    private IndexRec next;
    private IndexRec prev;

    public IndexRec() { //no parameter constructor
        value = "";
        index = 0;
        next = null;
        prev = null;
    }

    //parameter constructor with the index and value
    public IndexRec(String value, int index) {
        //Initializing the String to the parameters
        this.value = value;
        this.index = index;
        next = null;
        prev = null;
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
    //setter method for Next takes in an IndexRec
    public void setNext(IndexRec n) {
        next = n;
    }
    //getter method for Next, returns an IndexRec
    public IndexRec getNext() {
        return next;
    }
    //setter method for Prev takes in an IndexRec
    public void setPrev(IndexRec p) {
        prev = p;
    }
    //getter method for Prev, returns an IndexRec
    public IndexRec getPrev() {
        return prev;
    }

    //compareto method to compare index record objects and
    //get the value of that student to sort them in the index arrays
    public int compareto(IndexRec otherIndex) {
        return value.compareToIgnoreCase(otherIndex.getValue());
    }
}