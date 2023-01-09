// CAPSTONE HW
/** Evaluate the post fix expression
 * (when you see the variable such as a, you need to find the
 * corresponding value from linked list, then you push its value onto stack.
 * When you see the operator, you need to pop two element on top of stack,
 * then apply the operator and push results back to stack.
 * Last element in the stack is your final results.)
 * For example: a b + c *
 * When you see a, find value (3.0) from linked list then push onto stack
 * When you see b, find value (5.0) from linked list then push onto stack
 * When you see +, pop 3.0 and pop 5.0, then you will apply (+) to 3.0 and 5.0,
 *
 * Then you push 8.0 back to stack.
 **/

import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList data = new LinkedList();
        ObjectStack stack = new ObjectStack();

        // path variables
        String values_path = "C:\\Users\\krist\\Desktop\\Java\\CapstoneHW\\values.txt";
        String expression_path = "C:\\Users\\krist\\Desktop\\Java\\CapstoneHW\\expression.txt";

        // Read the text file and fill the linked list with the data
        try {
            //set up a read to values.txt
            File file = new File(values_path);

            Scanner values = new Scanner(file);

            //read it in a line at a time
            while (values.hasNextLine()) {
                //grab the next line
                String value = values.nextLine();

                String name = String.valueOf(value.charAt(0));

                //parse the float from the string conversion of the char at position 2
                float fl = Float.parseFloat(String.valueOf(value.charAt(2)));

                //now we can start the insert
                valueData newValues = new valueData(name, fl);

                data.insertFront(newValues);
            }
            values.close();

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened");
            System.exit(-1);
        }

        // Read the postfix expression from expression.txt
        try {
            File file = new File(expression_path);
            Scanner scanner = new Scanner(file);

            String expression = scanner.nextLine();

            for(int i = 0; i < expression.length() && expression.charAt(i) != '*'; i++){
                char variable = expression.charAt(i);

                if(Character.isLetter(variable)){
                    float fl = find(data.getHead(), String.valueOf(variable));
                    stack.push(new valueData(String.valueOf(variable), fl));

                    printStack(stack);

                }
                else if(variable == '+'){
                    if(stack.size() > 1){
                        float var1 = ((valueData) ((LinkedListNode) stack.pop()).getData()).getValue();
                        float var2 = ((valueData) ((LinkedListNode) stack.pop()).getData()).getValue();

                        stack.push((Object) new valueData("+", var1 + var2));
                    }
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened");
            System.exit(-1);
        }

    } // end of main

    /////////////////////////////////////////////////////////////////////////////
    // NAME: find
    // BEHAVIOR: Finds the node with given string and returns the corresponding
    //           value of the string.
    // PARAMETERS: head, var
    // RETURNS: value
    /////////////////////////////////////////////////////////////////////////////
    public static float find(LinkedListNode head, String variable) {
        LinkedListNode current = head;

        while(current != null){
            if (variable.equals(((valueData)current.getData()).getVariable())){
                valueData result = (valueData) current.getData();
                float value = result.getValue();
                return value;
            }
            current = current.getNext();
        }
        return 0;
    } // end of find

    /////////////////////////////////////////////////////////////////////////////
    // NAME: printStack
    // BEHAVIOR: print the stack from top to bottom
    // PARAMETERS: None
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public static void printStack(ObjectStack theStack){
        LinkedListNode crrnt = theStack.getHead();
        System.out.println("\nContents of stack from top to bottom");
        while( crrnt != null) {
            System.out.println(crrnt.getData());
            crrnt = crrnt.getNext();
        }
    }

} // end of program
