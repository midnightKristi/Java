import sun.awt.image.ImageWatched;

public class LinkedList {

    private LinkedListNode head;

    /////////////////////////////////////////////////////////////////////////////
    // NAME:
    // BEHAVIOR: Creates a new instance of LinkedList
    // PARAMETERS:
    // RETURNS:
    /////////////////////////////////////////////////////////////////////////////
    public LinkedList() {
        this.head = null;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME:
    // BEHAVIOR: Returns the head node of this node
    // PARAMETERS:
    // RETURNS:
    /////////////////////////////////////////////////////////////////////////////
    public LinkedListNode getHead() {
        return head;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: setHead
    // BEHAVIOR: Sets the next node of this node
    // PARAMETERS: head
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public void setHead(LinkedListNode head) {
        this.head = head;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: insertFront
    // BEHAVIOR: Add item at the front of linked list. Object is root of class
    //           tree. Object means that is can represent any object.
    // PARAMETERS:
    // RETURNS:
    /////////////////////////////////////////////////////////////////////////////
    public void insertFront(Object item) {
        LinkedListNode newNode = new LinkedListNode((valueData) item);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: removeFront
    // BEHAVIOR: Removes item at the front of linked list and return the object
    //           variable.
    // PARAMETERS:
    // RETURNS:
    /////////////////////////////////////////////////////////////////////////////
    public Object removeFront() {
        Object node = head;
        valueData data = (valueData) ((LinkedListNode) node).getData();
        System.out.print("Customer record:  " + data.toString() + "   successfully removed.");
        head = head.getNext();
        return node;
    }

} // end of class

