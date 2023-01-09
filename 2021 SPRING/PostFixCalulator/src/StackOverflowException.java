/**********************************************************
 * Program Name   : StackOverflowException.java
 * Author         : Michael Feuerstein
 * Date           : April 6, 2012
 * Course/Section : CSC 112-001
 * Program Description:  An extension of the StackException.
 *    Used for stack overflows.
 *
 **********************************************************/

/*
     UML Diagram
     -----------
     Class Name: StackOverflowException
     ----------------------------------
     Class Variables :
     ---------------
     Class Methods :
     +StackOverflowException()
     +StackOverflowException(String)
*/

public class StackOverflowException extends StackException
{
    public StackOverflowException()
    {
        super("Stack Overflow");
    }

    public StackOverflowException(String msg)
    {
        super(msg);
    }
}
