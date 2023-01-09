import java.util.EmptyStackException;

public class ObjectStack extends LinkedList implements Stack {
    private int size;

    /////////////////////////////////////////////////////////////////////////////
    // NAME:
    // BEHAVIOR: Constructs an
    // PARAMETERS:
    /////////////////////////////////////////////////////////////////////////////
    public ObjectStack(){
        super();
        size = 0;
    }


    /////////////////////////////////////////////////////////////////////////////
    // NAME: push
    // BEHAVIOR:
    // PARAMETERS:
    /////////////////////////////////////////////////////////////////////////////
    public void push(Object item) {
        /**
         *  USE INSERT FRONT (from Linked List. java)
         */
        this.insertFront(item);
        size++;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: pop
    // BEHAVIOR: pops the item on top pf stack
    // PARAMETERS: none
    // RETURNS: object
    /////////////////////////////////////////////////////////////////////////////
    public Object pop() throws EmptyStackException {
        // if the stack is empty, throw exception
        if (isEmpty()){
            throw new EmptyStackException();
        }
        /**
         * USE REMOVE FRONT (from Linked List. java)
         */
        Object popped = removeFront();
        size--;
        return popped;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: top
    // BEHAVIOR: returns the top os the stack
    // PARAMETERS: None
    // RETURNS: top
    /////////////////////////////////////////////////////////////////////////////
    public Object top() throws EmptyStackException {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return this.getHead();
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: size
    // BEHAVIOR: returns the current size of the array
    // PARAMETERS: None
    // RETURNS: size
    /////////////////////////////////////////////////////////////////////////////
    public int size(){
        return size;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: isEmpty
    // BEHAVIOR: returns true if the stack is empty, false otherwise
    // PARAMETERS: None
    // RETURNS: true or false
    /////////////////////////////////////////////////////////////////////////////
    public boolean isEmpty(){
        return (this.getHead() == null) ? true : false;
    }

    @Override
    public String toString() {
        String retString = "";

        for(LinkedListNode curr = getHead(); curr != null; curr = curr.getNext()){
            retString += String.valueOf(curr);
            if(curr.getNext() != null) retString += "\n";
        }

        return retString;
    }

} // end of class