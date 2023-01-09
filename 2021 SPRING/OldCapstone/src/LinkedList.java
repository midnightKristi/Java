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
    // NAME: getHead
    // BEHAVIOR: Returns the head node of this node
    // PARAMETERS: None
    // RETURNS: head
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
    // PARAMETERS: item
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public void insertFront(Object item) {
        LinkedListNode i = new LinkedListNode(item, null);
        if (head == null) {
            head = i;
        }
        else{
            i.setNext(head);
            head = i;
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: removeFront
    // BEHAVIOR: Removes item at the front of linked list and return the object
    //           variable.
    // PARAMETERS:
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public Object removeFront() {
        Object node = head;
        head = head.getNext();
        return node;
    }

} // end of class