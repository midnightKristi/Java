/*
    https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
    https://www.geeksforgeeks.org/implementation-of-dfs-using-adjacency-matrix/
 */


import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Graph{
    static int size;
    static int start = 0;
    static int[][] matrix;
    static int[][] adj;

    public static int[][] getMatrix() {
        try {
            // Read input.txt file
            Scanner input = new Scanner(new File("input.txt"));

            while (input.hasNextInt()) {

                size = input.nextInt(); // getting the matrix size first

                int[][] matrix = new int[size][size]; // initializing the matrix

                // filling the matrix with data from the input file
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {

                        try{
                            matrix[i][j] = input.nextInt();

                        }
                        catch (java.util.NoSuchElementException e) {
                            // e.printStackTrace();
                        }
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matrix;

    } // end getMatrix

    public static void BFSmain(boolean[] visited_bfs) {

        int[][] BFSmatrix = getMatrix();

        List<Integer> q = new ArrayList<>();
        q.add(start);

        // Set source as visited
        visited_bfs[start] = true;

        int vis;
        while (!q.isEmpty())
        {
            vis = q.get(0);

            // Print the current node
            System.out.print(vis + " ");
            q.remove(q.get(0));

            // For every adjacent vertex to
            // the current vertex
            for(int i = 0; i < size; i++)
            {
                if (adj[vis][i] == 1 && (!visited_bfs[i]))
                {

                    // Push the adjacent node to
                    // the queue
                    q.add(i);

                    // Set
                    visited_bfs[i] = true;
                }
            }
        }

        //print BFS sequence
        System.out.println("Breadth-first traversal of graph:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%5d ", BFSmatrix[i][j]);
            }
        }
        System.out.println();
    } // end BFS main

    public static void DFSmain (boolean[] visited_dfs)
    {
        int[][] DFSmatrix = getMatrix();

        //print the DFS Traversal sequence
        System.out.println("Depth-first traversal of graph:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%5d ", DFSmatrix[i][j]);
            }
        }
        System.out.println();
    } // end DFS main

    public static void main(String[] args) {

        // boolean array to prevent revisiting a vector
        boolean[] visited = new boolean[size];

        for(int i = 0; i<size; i++){
            visited[i] = false;
        }

        // Perform DFS
        BFSmain(visited);
        // Perform DFS
        DFSmain(visited);

    }

    } // end Graph class