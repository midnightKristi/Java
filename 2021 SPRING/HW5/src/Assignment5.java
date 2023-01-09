import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) throws IOException {
        LinkedList data = new LinkedList();

        try {
            FileReader fileIn = new FileReader("values.txt");
            BufferedReader in = new BufferedReader(fileIn);

            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    data.insertFront(line);
                }
            } //end while loop

            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("I/O error");
            System.exit(-1);
        }

        Scanner scan = new Scanner(System.in);

        while (true) {
            // Command prompt
            System.out.println("---------------------------------\n" +
                    "Customer list commands:\n" +
                    "---------------------------------\n" +
                    "   a - add a new record\n" +
                    "   f - find a variable value\n" +
                    "   r - remove front record\n" +
                    "    - \n" +
                    "   q - Quit\n" +
                    "---------------------------------\n");

            System.out.print("Enter command (a, f, r, n, or q): ");
            String command = scan.next();

            // add:
            if (command.equalsIgnoreCase("a")) {
                // scan for customer number, first name, last name, and balance
                System.out.println("Enter variable name: ");
                String name = scan.next();
                System.out.println("Enter the value: ");
                Double value = scan.nextDouble();

                valueData record = new valueData(name, value);
                data.insertFront(record);
                System.out.println(data.toString());
            } // end of add
            // find:
            else if (command.equalsIgnoreCase("f")) {
                // Prompt for the variable name
                System.out.println("Enter the variable name: ");
                String name = scan.next();
                // Display the corresponding record
                if (data.getHead() == null) {
                    System.out.println("Customer not found.");
                } else {
                    name.toString();
                }
            }   // end of find
            // Remove front record:
            else if (command.equalsIgnoreCase("r")) {
                System.out.println("Enter the variable to remove: ");
                String record = scan.next();
                data.removeFront();
                System.out.println(data.toString());
            } // end of remove front
            // Quit:
            else if (command.equalsIgnoreCase("q")) {
                // Terminate the program
                return;
            }
            // Illegal command entered:
            else {
                //Error message
                System.out.println("Command not recognized.");
            }

        } // end of while loop

    } // end of main

    /////////////////////////////////////////////////////////////////////////////
    // NAME: find
    // BEHAVIOR: Finds the node with given variable and returns the corresponding
    //           value of the variable.
    // PARAMETERS: head, variable
    // RETURNS: value
    /////////////////////////////////////////////////////////////////////////////
    public static float find(LinkedListNode head, String var) {
        LinkedListNode theNode = head;
        while (theNode != null) {
            String variable = var;
            double val = ((valueData) theNode.getData()).getValue();
            if (var == val) {
                return val;
                break;
            }
        }
    } // end of find

} // end of class
