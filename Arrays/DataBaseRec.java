public class DataBaseRec {
//3 string class variables
    private String firstName;
    private String lastName;
    private String idNumber;

//no parameter constructor to initialize the class variables into empty Strings
    public DataBaseRec() {
        firstName = "";            
        lastName = "";
        idNumber = "";

    }

//constructor with fName, lName, and id as parameters
    public DataBaseRec(String fName, String lName, String id) {
        this.firstName = fName;
        this.lastName = lName;
        this.idNumber = id;
    }

//get methods to get the id number, first name, and last name of the students from the database 
    public String getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//toString method to print out the student from the database
    public String toString() {
        return firstName + " " + lastName + " " + idNumber;
    }
}
