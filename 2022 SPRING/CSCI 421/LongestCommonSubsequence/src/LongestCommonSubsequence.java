/*
    NAME: Kristi LaVigne
    CLASS: CSCI 421 - 01
    SEMESTER: Spring 2022
    DESCRIPTION:
        Assignment 5: Determine a LCS of <1,0,0,1,0,1,0,1> and <0,1,0,1,1,0,1,1,0>.
        Give c and b matrix to show step by step result and also give the final solution.
        modified code from https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
*/

/* A Naive recursive implementation of LCS problem in java*/
public class LongestCommonSubsequence {

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs(int[] X, int[] Y, int m, int n)
    {
        if (m == 0 || n == 0)
            return 0;
        if (X[m-1] == Y[n-1])
            return 1 + lcs(X, Y, m-1, n-1);
        else
            return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
    } // end lcs

    /* Utility function to get max of 2 integers */
    int max(int a, int b)
    {
        return (a > b)? a : b;
    } // end max

    public static void main(String[] args)
    {
        LongestCommonSubsequence longest = new LongestCommonSubsequence();
        int[] s1 = {1,0,0,1,0,1,0,1};
        int[] s2 = {1,0,0,1,0,1,0,1};

        int m = s1.length;
        int n = s2.length;

        System.out.println("Length of LCS is" + " " +
                longest.lcs( s1, s2, m, n ) );
    } // end main

} // end LCS class
