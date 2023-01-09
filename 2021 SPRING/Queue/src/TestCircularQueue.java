import java.util.LinkedList;

public class TestCircularQueue {
    public static void main(String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 123);
        Employee johnDoe = new Employee("John", "Doe", 4567);
        Employee marySmith = new Employee("Mary", "Smith", 22);
        Employee mikeWilson = new Employee("Mike", "Wilson", 3245);
        Employee billEnd = new Employee("Bill", "End", 78);

        CircularArrayQueue queue = new CircularArrayQueue(5);

        queue.enqueue(janeJones);
        queue.enqueue(johnDoe);
        queue.enqueue(marySmith);
        queue.enqueue(mikeWilson);
        queue.enqueue(billEnd);
        queue.printQueue();
        System.out.println();

        queue.dequeue();
        queue.dequeue();
        queue.printQueue();
        System.out.println();

        System.out.println(queue.front());

        CircularArrayQueue queue2 = new CircularArrayQueue(5);

        queue2.enqueue(janeJones);
        queue2.enqueue(johnDoe);
        queue2.dequeue();
        queue2.enqueue(marySmith);
        queue2.dequeue();
        queue2.enqueue(mikeWilson);
        queue2.dequeue();
        queue2.enqueue(billEnd);
        queue2.dequeue();
        queue2.enqueue(janeJones);

        queue2.printQueue();
        System.out.println();


    }
}
