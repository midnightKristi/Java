/*
    NAME: Kristi LaVigne
    CLASS: CSCI 530
    ASSIGNMENT: Project 2
            Use Java to implement bottom-up LR parser for the following grammar and the parsing table.
            The sentence needs to be parsed is from keyboard input. The sentence always has a $ symbol at the end.
            Output requirements: output the current content of the stack and input after each action (either shift or reduce).
            Grammar rules:
                1. E -> E + T
                2. E -> T
                3. E -> T * F
                4. T -> F
                5. F -> (E)
                6. F -> id

            Online resources:
            https://www.geeksforgeeks.org/shift-reduce-parser-compiler/?ref=lbp
            https://www.tutorialspoint.com/compiler_design/compiler_design_bottom_up_parser.htm
            https://github.com/lfetch34/BottomUpLRParser
 */

import java.util.*;

public class LRparser {
    // Queue data structure to store and handle input string
    static Queue<String> inputQ;
    // Stack data structure to store and handle parsing stack
    static Stack<String> stack;


    public static void main(String[] args) {
        // Create new Queue for input
        inputQ = new LinkedList<String>();

        // Create new Stack for parsing stack
        stack = new Stack<String>();

        // Manually entered parsing action table
        String action[][] = {
                {"s5",null,null,"s4",null,null},
                {null,"s6",null,null,null,"accept"},
                {null,"r2","s7",null,"r2","r2"},
                {null,"r4","r4",null,"r4","r4"},
                {"s5",null,null,"s4",null,null},
                {null,"r6","r6",null,"r6","r6"},
                {"s5",null,null,"s4",null,null},
                {"s5",null,null,"s4",null,null},
                {null,"s6",null,null,"s11",null},
                {null,"r1","s7",null,"r1","r1"},
                {null,"r3","r3",null,"r3","r3"},
                {null,"r5","r5",null,"r5","r5"}
        };

        // Manually entered parsing goto table
        int goTo[][] = {
                {1,2,3},
                {0,0,0},
                {0,0,0},
                {0,0,0},
                {8,2,3},
                {0,0,0},
                {0,9,3},
                {0,0,10},
                {0,0,0},
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };

        // Get keyboard input of sentence to be parsed
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the sentence to be parsed without any spaces");
        System.out.println("valid lexemes are: id, +, *, (, ), and $ to end sentence): ");
        String sentence = scan.nextLine();
        scan.close();

        // Convert keyboard input String to char array
        char inQArr[] = sentence.toCharArray();

        // Call addInput method to add correct chars to String Queue
        addInput(inQArr, inputQ);

        // Stack always starts with 0 in it
        stack.add("0");

        // Print statements for output table formatting
        System.out.println();
        System.out.print("Stack");
        System.out.print(String.format("%22s %22s", "Input", "Action\n"));
        System.out.println("--------------" + "      " + "---------------"+ "      " + "------------");


        while (true) {
            // Retrieve state from top of Stack
            int state = Integer.parseInt(stack.peek());

            // Retrieve lexeme from head of Queue
            String lexeme = inputQ.peek();

            // Call determineAction method and store String result in act variable
            String act = nextAction(state, lexeme, action);

            // Test for null action returned, if so it means error in sentential form entered,
            // and breaks out of loop and causes program termination
            if (act == null) {
                System.out.print("Error, null action reached in parse table");
                break;
            }

            // Convert act String to char array
            char[] actArr = act.toCharArray();

            // needed to control output
            int i = 0;
            int L = 0;
            String space = "";

            // Test for accept state in char array, if so, print out last Stack, Input,
            // and Action results and break out of loop, causes program termination
            if (actArr[0] == 'a') {
                Object[] currStack = stack.toArray();
                // PRINTING STACK
                for (i = 0; i <currStack.length;i++) {
                    System.out.print(currStack[i]);
                }

                // determining how much padding
                if(i < 20){
                    L = 20 - i;
                }
                // creating the padding
                for(int j = 0; j < L; j++){
                    space += " ";
                }
                //spacing after printing Stack
                System.out.print(space);

                Object[] currInput = inputQ.toArray();
                // PRINTING INPUT
                for (i = 0; i <currInput.length;i++) {
                    System.out.print(currInput[i]);
                }
                space = " ";
                // determining how much padding
                if(currInput.length != 0 && currInput.length < 20){
                    L = 20 - currInput.length;
                }
                // creating the padding
                for(int j = 0; j < L; j++){
                    space += " ";
                }
                // space padding after Input
                System.out.print(space);

                // PRINTING ACTION
                for (i = 0; i <actArr.length;i++) {
                    System.out.print(actArr[i]);
                }
                break;

            } // end if

            // Convert current Stack to Object array
            Object[] currStack = stack.toArray();

            // PRINTING STACK - Print each element in Stack Object array
            for (i = 0; i <currStack.length;i++) {
                System.out.print(currStack[i]);
            }

            // determining how much padding
            if(i < 20){
                L = 20 - i;
            }
            // padding after Stack
            for(int j = 0; j < L; j++){
                space += " ";
            }
            // space padding after Stack
            System.out.print(space);

            // Convert input Queue to Object array
            Object[] currInput = inputQ.toArray();

            // PRINTING INPUT - Print each element in Queue Object array
            for (i = 0; i <currInput.length;i++) {
                System.out.print(currInput[i]);
            }
            space = " ";
            // determining how much padding
            if(currInput.length != 0 && currInput.length < 20){
                L = 20 - currInput.length;
            }
            // creating the padding
            for(int j = 0; j < L; j++){
                space += " ";
            }
            // space padding after Input
            System.out.print(space);

            // PRINTING ACTION - Print each element in current action char array
            for (i = 0; i <actArr.length;i++) {
                System.out.print(actArr[i]);
            }

            System.out.println();

            // Test for Shift 11 and handle it
            if (actArr[0] == 's' && actArr[1] == '1') {
                stack.add(inputQ.remove());
                String toAdd = Character.toString(actArr[1]) + Character.toString(actArr[2]);
                stack.add(toAdd);
                // Test for all other Shifts and handle them
            } else if (actArr[0] == 's') {
                stack.add(inputQ.remove());
                stack.add(Character.toString(actArr[1]));
                // Test for Reduce 2 and handle
            } else if (actArr[0] == 'r' && actArr[1]== '2') {
                //DO REDUCE
                stack.pop();
                stack.pop();
                String intUsed = stack.peek();
                stack.add("E");
                stack.add(Integer.toString(goTo[Integer.parseInt(intUsed)][0]));
                // Test for Reduce 4 and handle
            } else if (actArr[0] == 'r' && actArr[1]== '4') {
                stack.pop();
                stack.pop();
                String intUsed = stack.peek();
                stack.add("T");
                stack.add(Integer.toString(goTo[Integer.parseInt(intUsed)][1]));
                // Test for Reduce 6 and handle
            } else if (actArr[0] == 'r' && actArr[1]== '6') {
                stack.pop();
                stack.pop();
                String intUsed = stack.peek();
                stack.add("F");
                stack.add(Integer.toString(goTo[Integer.parseInt(intUsed)][2]));
                // Test for Reduce 1 and handle
            } else if (actArr[0] == 'r' && actArr[1]== '1') {
                for (i = 0; i < 6; i++) {
                    stack.pop();
                }
                String intUsed = stack.peek();
                stack.add("E");
                stack.add(Integer.toString(goTo[Integer.parseInt(intUsed)][0]));


                // Test for Reduce 3 and handle
            } else if (actArr[0] == 'r' && actArr[1]== '3') {
                for (i = 0; i < 6; i++) {
                    stack.pop();
                }
                String intUsed = stack.peek();
                stack.add("T");
                stack.add(Integer.toString(goTo[Integer.parseInt(intUsed)][1]));

                // Test for Reduce 5 and handle
            } else if (actArr[0] == 'r' && actArr[1]== '5') {
                for (i = 0; i < 6; i++) {
                    stack.pop();
                }
                String intUsed = stack.peek();
                stack.add("F");
                stack.add(Integer.toString(goTo[Integer.parseInt(intUsed)][2]));


            } // end else if

        } // end while loop

    } // end Main


    // addInput method takes a char array and String Queue, adds correct lexemes from input to Queue
    // Does not return any value but alters parameter Queue
    private static void addInput(char[] arr, Queue<String> q) {
        for (int i = 0; i < arr.length; i++) {
            // Test for and handle "id" lexeme by adding to Queue
            if (arr[i] == 'i' && arr[i+1]=='d') {
                String first = Character.toString(arr[i]);
                String second = Character.toString(arr[i+1]);
                q.add(first+second);
                // Test for char not being d, since d is part of id, then just add char to Queue
            } else if (arr[i] != 'd') {
                q.add(Character.toString(arr[i]));
            }

        }

    } // end addInput

    // Determines the correct action to execute, takes an int, String, and 2D String array as parameters
    // Returns action string if successful and valid, null otherwise
    private static String nextAction(int num, String s, String[][] arr) {
        String act = "";
        // Test if lexeme is null, if so, return null
        if (s == null) {
            return null;
        }
        // Tests for each lexeme and corresponding state value being valid entries in 2D array action table from parameter, if valid concatenate action String
        if (s.equalsIgnoreCase("id")&& arr[num][0] != null) {
            act += arr[num][0];
        } else if (s.equalsIgnoreCase("+")&& arr[num][1] != null) {
            act += arr[num][1];
        } else if (s.equalsIgnoreCase("*")&& arr[num][2] != null) {
            act += arr[num][2];
        } else if (s.equalsIgnoreCase("(")&& arr[num][3] != null) {
            act += arr[num][3];
        } else if (s.equalsIgnoreCase(")")&& arr[num][4] != null) {
            act += arr[num][4];
        } else if (s.equalsIgnoreCase("$") && arr[num][5] != null) {
            act += arr[num][5];
        }
        // Tests for each lexeme value and if the corresponding state and lexeme action value in 2D array is null, if so return null
        if (s.equalsIgnoreCase("id") && arr[num][0] == null) {
            return null;
        }

        if (s.equalsIgnoreCase("+") && arr[num][1] == null) {
            return null;
        }

        if (s.equalsIgnoreCase("*") && arr[num][2] == null) {
            return null;
        }

        if (s.equalsIgnoreCase("(") && arr[num][3] == null) {
            return null;
        }

        if (s.equalsIgnoreCase(")") && arr[num][4] == null) {
            return null;
        }
        if (s.equalsIgnoreCase("$") && arr[num][5] == null) {
            return null;
        }
        // Otherwise return action String
        return act;
    }

} // end nextAction

