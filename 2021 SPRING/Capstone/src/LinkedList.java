public class LinkedList {

    private LinkedListNode head;

    /////////////////////////////////////////////////////////////////////////////
    // NAME: LinkedList
    // BEHAVIOR: Creates a new instance of LinkedList, sets the head to null
    // PARAMETERS: none
    // RETURNS: nothing
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
        LinkedListNode newNode = (LinkedListNode) item;
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
    // PARAMETERS: none
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public Object removeFront() {
        Object node = head;
        //valueData data = (valueData) ((LinkedListNode) node).getData();
        //System.out.print("Customer record:  " + data.toString() + "   successfully removed.");
        head = head.getNext();
        return node;
    }

    @Override
    public String toString() {
        String retString = "";
        
        for(LinkedListNode curr = getHead(); curr != null; curr = curr.getNext()){
            valueData valueData = (valueData) curr.getData();
            retString += valueData.getVariable() + " = " + valueData.getValue();
            if(curr.getNext() != null) retString += "\n";
        }

        return retString;
    }

} // end of class