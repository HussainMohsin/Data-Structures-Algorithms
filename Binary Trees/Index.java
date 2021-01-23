public class Index {
    private IndexRec root;

    //constructor with zero parameter
    public Index() {
        root = null; // no nodes in tree yet
    }

    //getter method for root, returns the root
    public IndexRec getRoot() {
        return root;
    }

    public void insert(IndexRec newElement) {
        //declare and initialize IndexREc variables prev and rover to null
        IndexRec prev = null;
        IndexRec rover = null;

        boolean wentLeft = false; //check it the new element is going to be inserted in the left

        if (root == null) //check if the tree is empty
            root = newElement; //first element becomes the root
        else {
            prev = null; //previous indexRec so we don't run off the tree
            rover = root; // start at root
            while (rover != null) {
                prev = rover; //advance prev to rover

                //check if rover is less than new element, use our compare to method in IndexRec class
                if (rover.compareto(newElement) < 0) {
                    wentLeft = false; //went down the right
                    rover = rover.getRightChild(); //rover advances to the right child
                } else {
                    wentLeft = true; //went down the left
                    rover = rover.getLeftChild(); //rover advances to the left child
                } //end else
            } //end while
            //insert newNode here
            if (wentLeft) //check if we went down the left side of the tree
                prev.setLeftChild(newElement); //make prev's left pointer point to the new element
            else
                prev.setRightChild(newElement); //make prev's right pointer point to the new element
        }
    }

    // find student with given Id
    public IndexRec find(String id) {
        IndexRec currentIndex = root; // start at root
        //while the id we are looking for doesn't match the current id
        while (currentIndex.getValue().compareToIgnoreCase(id) != 0) {

            //check if the id we are looking for is less than the current id
            if (currentIndex.getValue().compareToIgnoreCase(id) > 0)
                currentIndex = currentIndex.getLeftChild(); //if it is less go left
            else
                currentIndex = currentIndex.getRightChild(); //if it is greater go right

            if (currentIndex == null) // if no child, or did not find the id
                return null; //return null
        }

        return currentIndex; //return what is found

    } // end find()

    public boolean delete(String id) {
        IndexRec parentIndex = root;
        IndexRec currentIndex = root; // start at root
        boolean wentleft = true; //check if it went left using a boolean variable

        //Check which side node is on.
        //While the current indexRec's id does not match the given id 
        while (currentIndex.getValue().compareToIgnoreCase(id) != 0) {
            //going down the tree
            parentIndex = currentIndex; //set parent equal to the child
            if (currentIndex.getValue().compareToIgnoreCase(id) > 0) { //if it is less go left
                wentleft = true; //set went left to true
                currentIndex = currentIndex.getLeftChild(); //go to the left child

            } else { //else go right
                wentleft = false; //set went left to false
                currentIndex = currentIndex.getRightChild(); //go to the right child
            }

            if (currentIndex == null) //end of the tree or did not find the matching id
                return false;
        }

        //Delete node with no children
        if (currentIndex.getLeftChild() == null && currentIndex.getRightChild() == null) {
            if (currentIndex == root) //if the current index is equal to the root
                root = null; // delete root, tree is empty

            //disconnecting current node from the parent
            else if (wentleft) //if the index went left
                parentIndex.setLeftChild(null); //have the parent's left pointer point to null
            else
                parentIndex.setRightChild(null); //have the parent's right pointer point to null
        }

        // if no right child, replace with left subtree
        else if (currentIndex.getRightChild() == null) {
            if (currentIndex == root) //if current indexRec is equal to the root
                root = currentIndex.getLeftChild(); //make the left child become the new root

            else if (wentleft) { //if it went left
            	//make parent left pointer point to current's left child
                parentIndex.setLeftChild(currentIndex.getLeftChild()); 
            }else { //if it went right
            	//make parent right pointer point to current's left child
                parentIndex.setRightChild(currentIndex.getLeftChild()); 
            }
        }

        // if no left child, replace with right subtree
        else if (currentIndex.getLeftChild() == null) {
            if (currentIndex == root) //check if current indexRec is equal to the root
                root = currentIndex.getRightChild(); //make the right child become the new root
            else if (wentleft) { //check if it went left
            	//make parent's left pointer point to current's right child
                parentIndex.setLeftChild(currentIndex.getRightChild()); 
            }else { //went right
            	//make parent's right pointer point to current's right child
                parentIndex.setRightChild(currentIndex.getRightChild()); 
            }
        }
        // Delete node with two children
        else {
            // get successor of indexRec to delete
            IndexRec successor = getSuccessor(currentIndex);
            if (currentIndex == root) //check if current indexRec is equal to the root
                root = successor; //make the successor become the new root
            else if (wentleft) { //check if it went left
            	//make parent's left pointer point to the successor
                parentIndex.setLeftChild(successor); 
            }else { //went right
            	//make parent's right pointer point to the successor
                parentIndex.setRightChild(successor); 
            }
            //make successor's left pointer point to current IndexRec's left child
            successor.setLeftChild(currentIndex.getLeftChild());
        }
        return true; //return true if IndexRec was found and deleted
    }

    private IndexRec getSuccessor(IndexRec newIndex) {
        IndexRec parent = newIndex; //parent is always current's parent
        IndexRec current = newIndex.getRightChild(); //make current be the right child
        IndexRec successor = newIndex;

        while (current != null) {
            //moving parent and current down the tree
            parent = successor;
            successor = current;
            current = current.getLeftChild(); //go to the left child
        }
        
      //check if the successor doesn't equal current's right child
        if (successor != newIndex.getRightChild()) { 
            //set parent's left pointer to point to the successor's right child
            //set successor's right pointer to point to the given IndexRec's right child
            parent.setLeftChild(successor.getRightChild());
            successor.setRightChild(newIndex.getRightChild());
        }

        return successor; //return the successor
    }
}