// Recursion means a function calls itself
// Recursion has a base case and recursive case
//      no recursion happens in the base case
//      every possible chain of recursive calls must eventually reach a base case

// Recursive case: call to the current method
// Each recursive call should be defined so that it makes progress towards a base case

import java.lang.*;

public class Recursion {
    public static void main(String[] args) {
        System.out.println("factorial(4): " + Fac(4));

        System.out.println("Mod(8,3): " + Mod(8, 3));

        System.out.println("Pal(aba): " + Pal("aba", 0, 2));

        System.out.println("Pow(2,3): " + Pow(2, 3));

        System.out.println("duplicate(String str, int x): " + duplicate("dog", 3));

        System.out.println("Fib(int a1, int a2, int n): " + Fib(1, 2, 7));
    } // end of main

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: Fac
    //BEHAVIOR: A recursive function called Fac which takes one positive integer argument (n) and returns n!
    //PARAMETERS: n - and int
    //RETURNS: the factorial of n
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int Fac(int n) {
        // base case
        if (n == 1)
            return 1;
            // recursive case
        else
            return n * Fac(n - 1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: Mod
    //BEHAVIOR: A recursive function that takes two positive integers and returns a % b - the remainder of
    //          the first int divided by the second int.
    //PARAMETERS: a & b
    //RETURNS: the remainder
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int Mod(int a, int b) {
        if (a < b)
            return a;
        else
            return Mod(a - b, b);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: Pal
    //BEHAVIOR: A recursive function called Pal which takes a string argument (str), and two integers,
    //          then determines if the string is a palindrome
    //PARAMETERS: i - the first position in the string
    //            x - the last position in the string
    //RETURNS: true if the string is a palindrome, false otherwise
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean Pal(String str, int i, int x) {
        // check the whole array
        if (i >= x)
            return true;
            // left side does not match right side
        else if (str.charAt(i) != str.charAt(x))
            return false;
        else
            // recursive case, move from left and right towards center
            return Pal(str, i + 1, x - 1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: Pow
    //BEHAVIOR: A recursive function that takes two positive integer arguments and returns x ^ y
    //PARAMETERS: x & y
    //RETURNS: x ^ y
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int Pow(int x, int y) {
        //base case
        if (y == 0)
            return 1;
        else
            //recursive case
            return x * Pow(x, y - 1);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: duplicate
    //BEHAVIOR: A recursive function called duplicate which take a string argument (str) and a non-negative int
    //          and returns a string which is str joined with itself x times.
    //PARAMETERS: str, x
    //RETURNS: string x times
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String duplicate(String str, int x) {
        if (x == 0)
            return "";
        else
            return str + duplicate(str, x - 1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: Fib
    //BEHAVIOR: A recursive function that takes three integers and returns the nth Fibonacci number
    //PARAMETERS: a1 - the first Fibonacci number
    //            a2 - the second Fibonacci number
    //            n - the index of the nth Fibonacci number to return
    //RETURNS: the nth Fibonacci number
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int Fib(int a1, int a2, int n) {
        if (n == 1)
            return a1;
        else if (n == 2)
            return a2;
        else
            // current number is sum of previous two numbers
            return Fib(a1, a2, n - 1) + Fib(a1, a2, n - 2);
    }

} // end of class
