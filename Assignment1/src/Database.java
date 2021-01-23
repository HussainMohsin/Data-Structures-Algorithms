import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database {
	//class Variables
    private DataBaseRec[] databaseRecords; //array of database Records
    private String firstName, lastName, id; //string for student info
    private int nextDBRec;
    private Index firstNameIndex, lastNameIndex, idIndex; //indexes

    //Scanner for user input
    Scanner input = new Scanner(System.in); 

    public Database() { //no parameter constructor
        databaseRecords = new DataBaseRec[100];
        nextDBRec = 0;
        firstNameIndex = new Index();
        lastNameIndex = new Index();
        idIndex = new Index();

        //try catch for to handle file exception
        try {
        	//TODO: use relative path
            File studentData = new File("C:\\Users\\Boulder\\Documents\\COSC311\\Assignment1\\src\\students.txt");
            
            Scanner fileIn = new Scanner(studentData); //Scanner to read in the file
            while (fileIn.hasNextLine() == true) { //loop through all the lines

                String firstName = fileIn.next(); //set first string in the line to firstname
                String lastName = fileIn.next(); //set second string in the line to lastname
                String id = fileIn.next();		//set third string in the line to id

                addNewStudent(firstName, lastName, id); //add student into the indexes and database using this method
            }
            fileIn.close(); //close file
        } catch (FileNotFoundException e) {
            System.out.println("File not found"); //catch exception if file isn't found
        }

    }

    public Database(int size) { //constructor with size as the parameter
    	//Initialize the array sizes to size passed in the parameter
        databaseRecords = new DataBaseRec[size]; 
        nextDBRec = 0;
        firstNameIndex = new Index(size);
        lastNameIndex = new Index(size);
        idIndex = new Index(size);
    }
    
    //method to search through the database to check if the id is found
    public int searchById(String id) {
    	IndexRec[] indexRecords = idIndex.getIndexRecords(); //getting the whole index Record
    	int idIndexPosition = idIndex.search(id); //index position of the student in the index record
    			
    	//if index was not found return -1 else return the index of the student in the database
        return idIndexPosition == -1 ? -1 : indexRecords[idIndexPosition].getIndex(); 
    }

    //function gets user input and adds it into the indexes and database
    public void addIt() {
    	
    	//get user input for first name, last name, and id
        System.out.print("Enter the student's first name: ");
        firstName = input.next();
        System.out.print("Enter the student's last name: ");
        lastName = input.next();
        System.out.print("Enter the student's ID number: ");
        id = input.next();

        if (searchById(id) < 0) { //if id is not in database add the new student
            addNewStudent(firstName, lastName, id); //add student into the indexes and database using this method
            System.out.println(databaseRecords[nextDBRec-1] + " was added.");

        } else {
        	//if searchById(id) returns anything but -1, that means the id exists in the database
            System.out.println("ID already exists"); 
        }
    }

    private void addNewStudent(String firstName, String lastName, String id) {
    	//add student to database
        databaseRecords[nextDBRec] = new DataBaseRec(firstName, lastName, id);
        
        //insert student info into index record arrays
        firstNameIndex.insert(new IndexRec(firstName, nextDBRec));
        lastNameIndex.insert(new IndexRec(lastName, nextDBRec));
        idIndex.insert(new IndexRec(id, nextDBRec));

        nextDBRec++; //increment the number of students
    }

    public void deleteIt() {
    	//get id from user
        System.out.print("Please enter Student ID: ");
        String id = input.next();

        //get the index of the student from the search method
        int recordIndex = searchById(id);

        //if student is found remove student info from the index record arrays
        if (recordIndex >= 0) {
            firstNameIndex.delete(databaseRecords[recordIndex].getFirstName());
            lastNameIndex.delete(databaseRecords[recordIndex].getLastName());
            idIndex.delete(databaseRecords[recordIndex].getIdNumber());
            
          //print out which student was deleted
            System.out.println(databaseRecords[recordIndex] + " was deleted.");
            
//            loop to permanently delete student from databse
//            for (int k = recordIndex; k < nextDBRec-1; k++) //Deleting from the database
//            	databaseRecords[k] = databaseRecords[k+1];
//            databaseRecords[nextDBRec-1]=null; //set last index to null, just incase          
// 			  nextDBRec--;
            
        } else
            System.out.println("Student not found"); //if student isn't found

    }
    
    public void findIt() {
    	//get id from user
        System.out.print("Please enter Student ID: ");
        String id = input.next();
        
      //get the index of the student from the search method
        int recordIndex = searchById(id);
        
       //if student is found print out student info
        if (recordIndex >= 0)
            System.out.println(databaseRecords[recordIndex]);
        else
            System.out.println("Student not found"); //if student isn't found

    }

    //All listing methods call printDatabase() with 2 parameters
    //the first parameter is an index, the index for what you are listing by
    //the second parameter is an int, 1 if it is ascending, or -1 for descending
    public void ListByIDAscending() {
        printDatabase(idIndex, 1);
    }

    public void ListByFirstAscending() {
        printDatabase(firstNameIndex, 1);
    }
    
    public void ListByLastAscending() {
        printDatabase(lastNameIndex, 1);
    }

    public void ListByIDDescending() {
        printDatabase(idIndex, -1);
    }

    public void ListByFirstDescending() {
        printDatabase(firstNameIndex, -1);
    }

    public void ListByLastDescending() {
        printDatabase(lastNameIndex, -1);
    }

    public void printDatabase(Index index, int order) {
        IndexRec i;
        int recordPosition;
        //index.printIt();
        
        if (order < 0) { //if order is -1 print in reverse order
            index.setIteratorEnd(); 
            //set the iterator to the index of last element, go get previous index record, until we come across null
            for (i = index.getIterator(); i != null; i = index.getIteratorPrev()) {
                recordPosition = i.getIndex(); //set recordPosition to the index of student
                System.out.println(databaseRecords[recordPosition]); //print database record at index recordPosition
            }
        } else { //if order is 1 print in forward order
            index.setIteratorBegin(); //set the iterator to the index of first element
            for (i = index.getIterator(); i != null; i = index.getIteratorNext()) {
                recordPosition = i.getIndex(); //set recordPosition to the index of student
                System.out.println(databaseRecords[recordPosition]); //print database record at index recordPosition
            }
        }
    }
}