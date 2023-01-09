// driver class

import java.io.IOException;
import java.util.*;

public class Assignment3 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        // CustomerList object
        CustomerList database = new CustomerList();
        // User prompt for file name
        System.out.print("Enter the file name for the customer records: ");
        String file = scan.next();
        // getCustomerList() call with given file name
        database.getCustomerList(file);

        while (true) {
            System.out.println();
            // Command prompt
            System.out.println("""
                    List of commands:\s
                     a - Add a new customer record
                     f - Find a customer record
                     q - Quit
                    """);
            System.out.print("Enter command (a, f, or q): ");
            String command = scan.next();

            if (command.equalsIgnoreCase("a")) {
                // scan for customer number, first name, last name, and balance
                System.out.println("Enter the customer number: ");
                int number = scan.nextInt();
                System.out.println("Enter the first name: ");
                String first = scan.next();
                System.out.println("Enter the last name: ");
                String last = scan.next();
                System.out.println("Enter the balance: ");
                double balance = scan.nextDouble();
                CustomerRecord record = new CustomerRecord(number, first, last, balance);
                // Create a customerRecord and store it in the database
                database.enterCustomerRecord(record);
            } else if (command.equalsIgnoreCase("f")) {
                // Prompt for the customer number
                System.out.println("Enter the customer number: ");
                int number = scan.nextInt();
                // Display the corresponding record
                CustomerRecord record = database.getCustomer(number);
                System.out.println(record);
            } else if (command.equalsIgnoreCase("q")) {
                // Prompt for file name
                System.out.print("Enter the file name for storing data: ");
                String fileName = scan.next();
                // Save information to the file
                database.saveCustomerList(fileName);
                // Terminate the program
                return;
            } else {
                //Error message
                System.out.println("Command not recognized");
            }
        }
    }
}
