// Winter 2020

import java.util.*;
public class COSC311Driver{
    public static void main(String[] args){
    	
		Database database=new Database();  
	   	int response;
	   	Scanner keyboard=new Scanner(System.in);        
        do
        {
            System.out.println("\n 1 Add a new student");
            System.out.println(" 2 Delete a student");
            System.out.println(" 3 Find a student by ID");
            System.out.println(" 4 List students by ID increasing");
            System.out.println(" 5 List students by first name increasing");
            System.out.println(" 6 List students by last name increasing");
            System.out.println(" 7 List students by ID decreasing");
            System.out.println(" 8 List students by first name decreasing");
            System.out.println(" 9 List students by last name decreasing");
            System.out.println("\n 0 End");
            System.out.print("Choice: ");
            response=keyboard.nextInt();
            System.out.println();
            
            switch (response)
            {
                case 1: database.addIt(); 	//Note: if the user enters an ID already in use, issue a warning and return to the menu
                        break;
                case 2: database.deleteIt();	//Note: output either "Deleted" or "ID not Found" and return to menu
                        break;
                case 3: database.findIt();	//Note: output the entire record or the message "ID not Found" and return to menu
                        break;
                case 4: database.ListByIDAscending();		
                        break;
                case 5: database.ListByFirstAscending();	
                        break;
                case 6: database.ListByLastAscending();
                        break;
                case 7: database.ListByIDDescending();
                        break;
                case 8: database.ListByFirstDescending();
                        break;
                case 9: database.ListByLastDescending();
                        break;
                default:                
            }
            
        } while (response!=0);
    }
}
