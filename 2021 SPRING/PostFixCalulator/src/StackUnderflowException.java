/**********************************************************
 * Program Name   : StackUnderflowException.java
 * Author         : Michael Feuerstein
 * Date           : April 6, 2012
 * Course/Section : CSC 112-001
 * Program Description:  An extension of the StackException.
 *    Used for stack underflows.
 *
 **********************************************************/

/*
     UML Diagram
     -----------
     Class Name: StackUnderflowException
     ------------------------------------
     Class Variables :
     ---------------
     Class Methods :
     +StackUnderflowException()
     +StackUnderflowException(String)
*/

public class StackUnderflowException extends StackException
{
    public StackUnderflowException()
    {
        super("Stack Underflow");
    }

    public StackUnderflowException(String msg)
    {
        super(msg);
    }
}