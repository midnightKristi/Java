// Circular Queue is also a linear data structure, which follows the principle of FIFO (first in first out)
// but instead of ending the queue at the last position, it again starts from the first position after the last,
// hence making the queue behave like a circular data structure.

// In the case of a circular queue, front will always point to the front of the queue, and tail (back) will
// always point to the end of the queue.

import java.util.NoSuchElementException;

public class CircularArrayQueue {
    // an array to implement the queue
    private Employee[] queue;
    private int front;
    private int back;

    // Initially the head and tail will be pointing to the same location, thus indicating an empty queue

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: CircularArrayQueue
    //BEHAVIOR: Constructs an array of size capacity and sets the front and back to the same location (zero)
    //PARAMETERS: capacity (an int)
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CircularArrayQueue(int capacity) {
        // array with the size of capacity
        queue = new Employee[capacity];
        front = 0;
        back = 0;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: enqueue
    //BEHAVIOR: Inserts an element at the end of the queue, doubles the array size if it is full
    //PARAMETERS: employee
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void enqueue(Employee employee) {
        // when the queue is full, we need to double the size of the array
        if (size() == queue.length - 1) {
            int numItems = size();
            Employee[] newArray = new Employee[2 * queue.length];

            // make sure the front is at the beginning position
            // copy the array between front and end of array to the beginning portion of array
            System.arraycopy(queue, front, newArray, 0, queue.length - front);
            // copy the beginning portion between 0 and back o the end of queue
            System.arraycopy(queue, 0, newArray, queue.length - front, back);

            queue = newArray;

            // reset front to 0 and back to numItems
            front = 0;
            back = numItems;
        }

        // add new employee to the back
        queue[back] = employee;
        // if back reaches the end, reset back to 0 since this is circular
        if (back < queue.length - 1) {
            back++;
        } else {
            back = 0;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: dequeue
    //BEHAVIOR: removes and returns the element at the front of the queue
    //PARAMETERS: none
    //RETURNS: Employee
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Employee dequeue() {
        // if queue is empty, throw exception
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        // get the element from the front queue
        Employee employee = queue[front];
        // make sure the information at front is erased
        queue[front] = null;
        // increase front by one
        front += 1;
        // if queue is empty, reset front and back
        if (size() == 0) {
            front = 0;
            back = 0;
        }
        // if front reach end of array, reset the front
        else if (front == queue.length) {
            front = 0;
        }

        return employee;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: front
    //BEHAVIOR: Returns the element at the front without removing it
    //PARAMETERS: none
    //RETURNS: element at front
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Employee front() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return queue[front];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: size
    //BEHAVIOR: Returns how many elements are in the array, handles two cases:
    //          case 1 - it is normal queue, case 2 - the queue is wrapped
    //PARAMETERS: none
    //RETURNS: size of queue
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int size() {
        if (front <= back) {
            return back - front;
        } else {
            return back - front + queue.length;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: printQueue
    //BEHAVIOR:
    //PARAMETERS:
    //RETURNS:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void printQueue() {
        System.out.println("printQueue from front to back");
        // this is normal case
        if (front <= back) {
            for (int i = front; i < back; i++) {
                System.out.print(" " + queue[i]);
            }
        } else { // this is wrapped case
            for (int i = front; i < queue.length; i++) {
                System.out.print(" " + queue[i]);
            }
            for (int i = 0; i < back; i++) {
                System.out.println(queue[i]);
            }
        }
        System.out.println();
    }

} // end of class

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//NAME:
//BEHAVIOR:
//PARAMETERS:
//RETURNS:
////////////////////////////////////////////////////////////////////////////////////////////////////////////