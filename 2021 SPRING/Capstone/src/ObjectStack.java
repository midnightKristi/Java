import java.util.EmptyStackException;

public class ObjectStack extends LinkedList implements Stack {
    private int size;

    /////////////////////////////////////////////////////////////////////////////
    // NAME: ObjectStack
    // BEHAVIOR: Constructs a stack of objects
    // PARAMETERS: none
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public ObjectStack(){
        super();
        size = 0;
    }


    /////////////////////////////////////////////////////////////////////////////
    // NAME: push
    // BEHAVIOR: puts an object in front of the stack and increases the size
    // PARAMETERS: item
    /////////////////////////////////////////////////////////////////////////////
    public void push(Object item) {
        insertFront((LinkedListNode) item);
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
        size--;
        return removeFront();
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: size
    // BEHAVIOR: returns the current size of the array
    // PARAMETERS: None
    // RETURNS: size
    /////////////////////////////////////////////////////////////////////////////
    public Object top() throws EmptyStackException {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return getHead();
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
