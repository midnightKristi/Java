/**********************************************************
 * Program Name   : StackException.java
 * Author         : Michael Feuerstein
 * Date           : April 6, 2012
 * Course/Section : CSC 112-001
 * Program Description:  An extension of the RuntimeException.
 *    Used for stack exceptions.
 *
 **********************************************************/

/*
     UML Diagram
     -----------
     Class Name: StackException
     ---------------------------
     Class Variables :
     ---------------
     Class Methods :
     +StackException()
     +StackException(String)
*/

public class StackException extends RuntimeException
{
    public StackException()
    {
    }

    public StackException(String msg)
    {
        super(msg);
    }
}