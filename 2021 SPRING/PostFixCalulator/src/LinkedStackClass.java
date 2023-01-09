/**********************************************************
 * Program Name   : LinkedStackClass.java
 * Author         : Michael Feuerstein
 * Date           : April 6, 2012
 * Course/Section : CSC 112-001
 * Program Description:  This class and its methods act as a
 *    pseudo array by creating managing and manipulating a
 *    stack related data. The data itself is contained
 *    within a node that houses both the data and the address
 *    of the next node in the list.
 *
 **********************************************************/

/*
     UML Diagram
     -----------
     Class Name: LinkedStackClass
     ---------------------------
     Class Variables :
     -StackNode: class
     -stackTop: StackNode
     ---------------
     Class Methods :
     +LinkedStackClass()
     +LinkedStackClass(LinkedStackClass)
     +initializeStack(): void
     +isEmptyStack(): boolean
     +isFullStack(): boolean
     +push(DataElement): void
     +top() DataElement
     +pop(): void
     -copy(LinkedStackClass): void
     +copyStack(LinkedStackClass): void
*/

public class LinkedStackClass
{
        //Definition of the node
    private class StackNode
    {
        DataElement info;
        StackNode link;
    }

    private StackNode stackTop;     //reference variable to the top
                                    //element of the stack

        //default constructor
        //Postcondition: stackTop = null
    public LinkedStackClass()
    {
        stackTop = null;
    }

        //Method to initialize the stack to an empty state.
        //Postcondition: stackTop = null
    public void initializeStack()
    {
        stackTop = null;
    }// end initializeStack



        //Method to determine whether the stack is empty.
        //Postcondition: Returns true if the stack is empty;
        //               otherwise, returns false.
    public boolean isEmptyStack()
    {
        return(stackTop == null);
    }


        //Method to determine whether the stack is full.
        //Postcondition: Returns true if the stack is full;
        //               otherwise, returns false.
    public boolean isFullStack()
    {
        return false;
    }

        //Method to add newItem to the stack.
        //Postcondition: The stack is changed and newItem
        //               is added to the top of stack.
    public void push(DataElement newElement)
    {
        StackNode newNode; //reference variable to create
                            //the new node

        newNode = new StackNode(); //create the new node
        newNode.info = newElement.getCopy();
        newNode.link = stackTop;     //insert newNode before
                                     //stackTop
        stackTop = newNode;          //set stackTop to point to
                                     //the top element
    } //end push


        //Method to return the top element of the stack.
        //Precondition: The stack exists and is not empty.
        //Postcondition: If the stack is empty, the method throws
        //               StackUnderflowException; otherwise, a
        //               reference to a copy of the top element
        //               of the stack is returned.
    public DataElement top() throws StackUnderflowException
    {
        if(stackTop == null)
            throw new StackUnderflowException();

        return stackTop.info.getCopy();
    }//end top


        //Method to remove the top element of the stack.
        //Precondition: The stack exists and is not empty.
        //Postcondition: The stack is changed and the top
        //               element is removed from the stack.
        //               If the stack is empty, the method throws
        //               StackUnderflowException
    public void pop()throws StackUnderflowException
    {
        if(stackTop == null)
            throw new StackUnderflowException();

        stackTop = stackTop.link;   //advance stackTop to the
                                    //next node
    }//end pop


        //copy constructor
   public LinkedStackClass(LinkedStackClass otherStack)
    {
        copy(otherStack);
    }//end copy constructor

        //Method to make a copy of otherStack.
        //Postcondition: A copy of otherStack is created and
        //               assigned to this stack.
   public void copyStack(LinkedStackClass otherStack)
    {
        if(this != otherStack)
            copy(otherStack);
    }

        //Method to make a copy of otherStack.
        //This method is used only to implement the methods
        //copyStack and copy constructor
        //Postcondition: A copy of otherStack is created and
        //               assigned to this stack.
    private void copy(LinkedStackClass otherStack)
    {
        stackTop = null;   //make this stack empty
        System.gc();

        if(!otherStack.isEmptyStack()) //otherStack is not empty

            stackTop = otherStack.stackTop;  //copies the other stack's
                                             //stackTop
    }
}