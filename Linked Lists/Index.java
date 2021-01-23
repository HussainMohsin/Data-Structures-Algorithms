public class Index {
    private IndexRec front, back;

    //constructor with zero parameter
    //initialize front and back to null
    public Index() {
        front = null;
        back = null;
    }
    //getter function for front
    public IndexRec getFront() {
        return front;
    }
    //getter function for front
    public IndexRec getBack() {
        return back;
    }
    //insertion function for a doubly double ended
    //inserts the data sorted
    public void insert(IndexRec newIndexRec) {
        //declare currentRec of type IndexRec
        IndexRec currentRec = front; //Initialize currentRec to front

        if (front == null) { //empty list
            front = newIndexRec;
            back = newIndexRec;
        } else {
            //loop to look through the linkedList of IndexRec's values and compare them to IndexRec passed in
            while (currentRec.getNext() != null && currentRec.compareto(newIndexRec) < 0)
                currentRec = currentRec.getNext();

            //Insert newIndexRec before currentRec 
            //calling the compareto() method in my IndexRec class to compare the values of the IndexRecs
            if (currentRec.compareto(newIndexRec) > 0) { //if the value is less than the current
                newIndexRec.setNext(currentRec);
                newIndexRec.setPrev(currentRec.getPrev());

                if (currentRec.getPrev() != null) //if the last element was not null
                    currentRec.getPrev().setNext(newIndexRec);
                else //inserting before front
                    front = newIndexRec; //inserting before front, initialize front to the new Index Record

                currentRec.setPrev(newIndexRec);

            } else { //Insert newIndexRec after currentRec
                newIndexRec.setPrev(currentRec);
                newIndexRec.setNext(currentRec.getNext());

                if (currentRec.getNext() != null) //if the next element not null
                    currentRec.getNext().setPrev(newIndexRec);
                else
                    back = newIndexRec; //inserting after back, initialize back to the new Index Record

                currentRec.setNext(newIndexRec);
            }
        }
    }

    public IndexRec search(String indexValue) {
        IndexRec currentRec = front; //if list is empty currentRec gets assigned to null

        //loop to look through the linkedList of IndexRec's values and compare them to the string passed in
        while (currentRec != null && currentRec.getValue().compareToIgnoreCase(indexValue) != 0)
            currentRec = currentRec.getNext();


        return currentRec; //returns the record if found else return null
    }
    //method to over write the node we want to delete
    public IndexRec delete(String key) {
        IndexRec currentRec;
        //call the search method that returns the indexRec if it is found
        currentRec = search(key); //search method takes our string key parameter

        if (currentRec != null) { //search method returns null if indexRec isn't found

            if (currentRec.getPrev() == null) //if the node we want to delete is front
                front = currentRec.getNext(); //set front to the node after currentRec
            else //else make the node before currentRec point to the node after currentRec
                currentRec.getPrev().setNext(currentRec.getNext());

            if (currentRec.getNext() == null) //if the node we want to delete is back
                back = currentRec.getPrev(); //set back to the node before currentRec
            else //else make the node  after currentRec point to the node before currentRec
                currentRec.getNext().setPrev(currentRec.getPrev());
        }

        return currentRec; //return a reference to the deleted node
    }

}