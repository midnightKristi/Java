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

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList data = new LinkedList();
        ObjectStack stack = new ObjectStack();

        // path variables
        String values_path = "C:\\Users\\krist\\Desktop\\Java\\Capstone\\values.txt";
        String expression_path = "C:\\Users\\krist\\Desktop\\Java\\Capstone\\expression.txt";

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
                valueData valueData = new valueData(name, fl);
                LinkedListNode linkedListNode = new LinkedListNode((Object) valueData);
                data.insertFront((Object) linkedListNode);
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

            for(int i = 0; i < expression.length(); i++){
                Character variable = expression.charAt(i);
                String name = String.valueOf(variable);
                System.out.println(">" + name);
                //really simple way to have flexible code for literally and letter and no worries on caps
                if(Character.isLetter(variable)){
                    //find the variable
                    Float fl = find(data.getHead(), name);
                    //ignore ones you don't find
                    if(fl != 0){
                        //make a new node and push it on the stack
                        LinkedListNode linkedListNode = new LinkedListNode((Object) new valueData(name, fl));
                        stack.push((Object) linkedListNode);
                    }
                }
                //if we're going to add
                else if(Character.compare(variable, '+') == 0){
                    //if theres enough to work with
                    if(stack.size() > 1){
                        //pop last two vars and get their values
                        valueData var1 = (valueData) ((LinkedListNode) stack.pop()).getData();
                        valueData var2 = (valueData) ((LinkedListNode) stack.pop()).getData();

                        //i'm not sure what you'd name the new variable so i went with 'var1.name+var2.name'
                        String new_name = var1.getVariable() + "+" + var2.getVariable();
                        //add the values
                        Float new_value = var1.getValue() + var2.getValue();

                        //create new variable and push it on the stack
                        LinkedListNode linkedListNode = new LinkedListNode((Object) new valueData(new_name, new_value));
                        stack.push((Object) linkedListNode);
                    }
                    else{
                        System.out.println("Stack Size: " + stack.size());
                    }
                }
                else if(Character.compare(variable, '*') == 0){
                    //if theres enough to work with
                    if(stack.size() > 1){
                        //pop last two vars and get their values
                        valueData var1 = (valueData) ((LinkedListNode) stack.pop()).getData();
                        valueData var2 = (valueData) ((LinkedListNode) stack.pop()).getData();

                        //i'm not sure what you'd name the new variable so i went with 'var1.name+var2.name'
                        String new_name = var1.getVariable() + "*" + var2.getVariable();
                        //add the values
                        Float new_value = var1.getValue() * var2.getValue();

                        //create new variable and push it on the stack
                        LinkedListNode linkedListNode = new LinkedListNode((Object) new valueData(new_name, new_value));
                        stack.push((Object) linkedListNode);
                    }
                    else{
                        System.out.println("Stack Size: " + stack.size());
                    }
                }
                System.out.println("content of stack from top to bottom\n" + String.valueOf(stack));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened");
            System.exit(-1);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: find
    // BEHAVIOR: Finds the node with given string and returns the corresponding
    //           value of the string.
    // PARAMETERS: head, var
    // RETURNS: value
    /////////////////////////////////////////////////////////////////////////////
    public static float find(LinkedListNode head, String var) {
        for(LinkedListNode curr = head; head != null; curr = curr.getNext()){
            valueData temp = (valueData) curr.getData();
            if(temp.getVariable().equals(var)){
                return temp.getValue();
            }
        }

        return 0;
    } // end of find

}
