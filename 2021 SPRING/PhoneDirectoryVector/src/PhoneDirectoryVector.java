// PhoneDirectoryVector.java
// The vector class implements a growable array of objects
// Like an array, it contains components that can be accessed using an integer index
// However, the size of a vector can grow or shrink as needed to accommodate adding
// and removing items after the vector has been created


// Stores names and telephone numbers and allows the numbers to be looked up.
// Names and/or phone numbers can also be changed. The user is given a menu of four commands:
//              a - Add a new phone number
//              c - Change name and/or number
//              f - Find a phone number
//              q - Quit

import java.util.*;
import java.io.*;

public class PhoneDirectoryVector {
    // class variables
    private static final String DATA_FILE = "records.dat";
    private static Vector records;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Read records from data file
        readRecords();

        // Display list of commands
        System.out.println("---------------------------------\n" +
                "Phone directory commands:\n" +
                "---------------------------------\n" +
                "   a - Add a new phone number\n" +
                "   c - Change name and/or number\n" +
                "   f - Find a phone number\n" +
                "   q - Quit\n" +
                "---------------------------------\n");

        // Read and execute commands
        while(true){
            // Prompt user to enter a command
            System.out.print("Enter command (a, f, or q): ");
            String command = scan.next();

            //checking command, responding appropriately
            if (command.equalsIgnoreCase("a")) {
                addNumber();
            }
            else if (command.equalsIgnoreCase("f")) {
                findNumber();
            }
            else if (command.equalsIgnoreCase("q")) {
                // save the record vector in the data file
                // and terminate the program
                saveRecords();
                return;
            } else {
                System.out.println("Command was not recognized; " + "please enter only a, c, f, or q.");
            }

            System.out.println();
        }

    } // end of main

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//NAME: addNumber
//BEHAVIOR: Prompts the user for a name and phone number, then creates a phone record and stores it
//          in the records vector
//PARAMETERS: none
//RETURNS: nothing
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void addNumber(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new name: ");
        String name = scan.nextLine().trim();

        System.out.println("Enter new phone number: ");
        String number = scan.nextLine().trim();

        PhoneRecord cr = new PhoneRecord(name, number);
        System.out.println(cr);

        // add the element to the end of the vector, increasing size of vector by 1
        records.addElement();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//NAME: addNumber
//BEHAVIOR: Prompts the user for a name and phone number, then creates a phone record and stores it
//          in the records vector
//PARAMETERS: none
//RETURNS: nothing
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void findNumber(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name to look up: ");
        String key = scan.nextLine().trim().toLowerCase();

        for(int i = 0; i < records.size(); i++){
            // elementAt will return object at specified position
            PhoneRecord currentRecord = (PhoneRecord) records.elementAt(i);
            String name = currentRecord.getName().toLowerCase();

            if(name.startsWith(key)){
                System.out.println(currentRecord.getName() + " " + currentRecord.getNumber());
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//NAME: readRecords
//BEHAVIOR: Restores the records vector to its previous state by reading it (as a single object) from the
//          data file. Creates an empty vector if the file does not exist or cannot be read.
//PARAMETERS: none
//RETURNS: nothing
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void readRecords(){
        try{
            FileInputStream fileIn = new FileInputStream(DATA_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // readObject will read object from text file
            records = (Vector) in.readObject();
            in.close();
        } catch(Exception e){
            System.out.println(DATA_FILE + " does not exist or cannot be read\n");
            records = new Vector();

        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//NAME: saveRecords
//BEHAVIOR: Saves the vector (as a single object) by writing it to the data file.
//PARAMETERS: none
//RETURNS: nothing
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void saveRecords(){
        try{
            FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // writeObject will write object from text file
            out.wrtieObject(records);
            out.close();
        } catch(IOException e){
            System.out.println("Error writing to " + DATA_FILE);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//NAME: writeObject
//BEHAVIOR:
//PARAMETERS:
//RETURNS:
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void writeObject(){

    }

} // end of class


