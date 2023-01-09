/**********************************************************
 * Program Name   : IntElement.java
 * Author         : Michael Feuerstein
 * Date           : April 4, 2012
 * Course/Section : CSC 112-001
 * Program Description: An extension of the abstract class
 *    DataElement, utilizes the abstract classes from
 *    DataElement to create manage and manipulate integers.
 *
 **********************************************************/

/*
     UML Diagram
     -----------
     Class Name: IntElement
     ---------------------------
     Class Variables :
     #num: String
     ---------------
     Class Methods :
     +StringElement()
     +StringElement(int)
     +StringElement(IntElement)
     +setNum(int): void
     +getNum(): int
     +equals(DataElement): boolean
     +compareTo(DataElement): int
     +makeCopy(DataElement): void
     +getCopy(): DataElement
     +toString(): String
*/

public class IntElement extends DataElement
{
    protected int num;

      //default constructor
    public IntElement()
    {
        num = 0;
    }

      //constructor with a parameter
    public IntElement(int x)
    {
        num = x;
    }
      //copy constructor
    public IntElement(IntElement otherElement)
    {
        num = otherElement.num;
    }

      //Method to set the value of the instance variable num.
      //Postcondition: num = x;
    public void setNum(int x)
    {
        num = x;
    }

      //Method to return the value of the instance variable num.
      //Postcondition: The value of num is returned.
    public int getNum()
    {
        return num;
    }
      //method to compare two int elements
      //returns a boolean
    public boolean equals(DataElement otherElement)
    {
        IntElement temp = (IntElement) otherElement;
        return (num == temp.num);
    }

      //method to comapre the difference between two
      //int elements
      //returns the difference
    public int compareTo(DataElement otherElement)
    {
        IntElement temp = (IntElement) otherElement;
        return (num - temp.num);
    }

      //Accepts an int element and makes a copy of it
    public void makeCopy(DataElement otherElement)
    {
        IntElement temp = (IntElement) otherElement;
        num = temp.num;
    }
      //Returns a copy of this class
    public DataElement getCopy()
    {
        IntElement temp = new IntElement(num);
        return temp;
    }

      //prints out the class data
    public String toString()
    {
        return String.valueOf(num);
    }
}